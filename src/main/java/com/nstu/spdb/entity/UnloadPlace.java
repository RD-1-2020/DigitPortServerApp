package com.nstu.spdb.entity;

import com.nstu.spdb.enums.UnloadStatus;

import javax.persistence.*;

@Entity
@Table
public class UnloadPlace extends AbstractPersistableIdentity<Long> {
    @Column
    @Enumerated(EnumType.STRING)
    private UnloadStatus status;

    @JoinColumn
    @OneToOne
    private Order order;

    public UnloadStatus getStatus() {
        return status;
    }

    public void setStatus(UnloadStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
