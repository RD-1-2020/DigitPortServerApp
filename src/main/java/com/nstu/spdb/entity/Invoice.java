package com.nstu.spdb.entity;

import com.nstu.spdb.enums.InvoiceType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Invoice extends AbstractPersistableIdentity<Long> {

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @Column
    private String number;

    @Column
    private Date dateSupply;

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

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public Date getDateSupply() {
        return dateSupply;
    }

    public void setDateSupply(Date dateSupply) {
        this.dateSupply = dateSupply;
    }
}
