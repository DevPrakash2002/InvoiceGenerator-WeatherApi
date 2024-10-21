package com.freight.fox.service;

import com.freight.fox.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PdfGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorService.class);
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public PdfGeneratorService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(Invoice invoice) {
        try {
            // Create Thymeleaf context
            Context context = new Context();
            context.setVariable("invoice", invoice);

            // Process template to HTML
            logger.info("Processing template with Thymeleaf");
            String processedHtml = templateEngine.process("invoice-template", context);
            
            if (processedHtml == null || processedHtml.isEmpty()) {
                throw new RuntimeException("Template processing resulted in empty HTML");
            }

            // Setup PDF renderer
            ITextRenderer renderer = new ITextRenderer();
            
            logger.info("Setting up PDF renderer");
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();

            // Generate PDF
            logger.info("Generating PDF");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.createPDF(outputStream, true);
            outputStream.close();

            logger.info("PDF generation completed");
            return outputStream.toByteArray();
            
        } catch (Exception e) {
            logger.error("PDF generation failed", e);
            throw new RuntimeException("Failed to generate PDF: " + e.getMessage(), e);
        }
    }
}