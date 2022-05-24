package com.nstu.spdb.service;

import com.nstu.spdb.dto.InvoiceDto;
import com.nstu.spdb.entity.Invoice;
import com.nstu.spdb.repository.InvoiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createFromDto(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Invoice invoice = new Invoice();
        invoice.setTitle(invoiceDto.getTitle());
        invoice.setNumber(invoiceDto.getNumber());
        invoice.setId(invoiceDto.getId());
        invoice.setDateSupply(invoiceDto.getDateSupply());

        return invoice;
    }

    public Invoice createAndSave(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        if (StringUtils.isNotBlank(invoiceDto.getNumber()) && StringUtils.isNotBlank(invoiceDto.getTitle())) {
            Invoice invoice = invoiceRepository.getByNumberAndTitle(invoiceDto.getNumber(), invoiceDto.getTitle());
            if (invoice != null) {
                return invoice;
            }
        }

        return invoiceRepository.save(createFromDto(invoiceDto));
    }
}
