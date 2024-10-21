package com.freight.fox.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class InvoiceItem {
    private String name;
    private int quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
