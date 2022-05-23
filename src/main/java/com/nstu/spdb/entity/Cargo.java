package com.nstu.spdb.entity;

import javax.persistence.*;

@Entity
@Table
public class Cargo extends AbstractPersistableIdentity<Long> {

    @Column
    private Long weight;

    @Column
    private String title;

    @JoinColumn
    @OneToOne
    private Invoice invoice;

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
