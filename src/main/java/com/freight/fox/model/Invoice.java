package com.freight.fox.model;

import java.util.ArrayList;

import com.itextpdf.layout.element.List;

import lombok.Data;

@Data
public class Invoice {
    private String seller;
    private String sellerAddress;
    private String sellerGstin;
    private String buyer;
    private String buyerAddress;
    private String buyerGstin;
    private ArrayList<InvoiceItem> items;
}
