package com.nstu.spdb.dto;

import com.nstu.spdb.entity.Invoice;

import java.io.Serializable;
import java.util.Date;

public class InvoiceDto implements Serializable {
    private Long id;
    private String title;
    private String number;
    private Date dateSupply;

    public InvoiceDto(Invoice invoice) {
        if (invoice == null) {
            return;
        }

        this.id = invoice.getId();
        this.title = invoice.getTitle();
        this.number = invoice.getNumber();
        this.dateSupply = invoice.getDateSupply();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateSupply() {
        return dateSupply;
    }

    public void setDateSupply(Date dateSupply) {
        this.dateSupply = dateSupply;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
