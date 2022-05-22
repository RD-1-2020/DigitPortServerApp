package com.nstu.spdb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_cargo")
public class Order extends AbstractPersistableIdentity<Long> {

    @Column
    private Date createDate;

    @Column
    private Date closeDate;

    @OneToOne
    @JoinColumn
    private Client client;

    @OneToMany
    @JoinColumn(name = "cargos")
    private List<Cargo> cargos;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Cargo> getCargos() {
        if (cargos == null) {
            cargos = new ArrayList<>();
        }

        return cargos;
    }
}
