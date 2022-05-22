package com.nstu.spdb.service;

import com.nstu.spdb.dto.InvoiceDto;
import com.nstu.spdb.entity.Invoice;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    public Invoice createFromDto(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Invoice invoice = new Invoice();
        invoice.setTitle(invoiceDto.getTitle());
        invoice.setNumber(invoiceDto.getNumber());
        invoice.setId(invoiceDto.getId());
        invoice.setDateSupply(invoiceDto.getDateSupply());
        invoice.setType(invoiceDto.getType());

        return invoice;
    }
}
