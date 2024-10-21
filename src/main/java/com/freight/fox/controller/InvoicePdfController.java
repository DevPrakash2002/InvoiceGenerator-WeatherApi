package com.freight.fox.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.freight.fox.model.Invoice;
import com.freight.fox.service.PdfGeneratorService;

@RestController
@RequestMapping("/api/invoice")
public class InvoicePdfController {

    private static final Logger logger = LoggerFactory.getLogger(InvoicePdfController.class);
    private final PdfGeneratorService pdfGeneratorService;

    @Autowired
    public InvoicePdfController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping(value = "/generate-invoice", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestBody Invoice invoice) {
        try {
            logger.info("Received request to generate PDF");
            byte[] pdfContent = pdfGeneratorService.generatePdf(invoice);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "attachment; filename=invoice.pdf");
            headers.setCacheControl("no-cache, no-store, must-revalidate");
            headers.setPragma("no-cache");
            headers.setExpires(0L);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(pdfContent.length)
                    .body(pdfContent);
                    
        } catch (Exception e) {
            logger.error("Failed to generate PDF", e);
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Failed to generate PDF: " + e.getMessage()
            );
        }
    }
}