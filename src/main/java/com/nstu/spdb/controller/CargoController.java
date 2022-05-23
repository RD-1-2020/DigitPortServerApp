package com.nstu.spdb.controller;

import com.nstu.spdb.dto.CargoDto;
import com.nstu.spdb.dto.InvoiceDto;
import com.nstu.spdb.entity.Invoice;
import com.nstu.spdb.service.CargoService;
import com.nstu.spdb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "cargo/getCargos")
    public ResponseEntity<List<CargoDto>> getAllCargos() {
        List<CargoDto> cargoDtos = cargoService.getAllCargosWithoutInvoice();
        if (cargoDtos == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cargoDtos, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "cargo/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity updateInvoiceByCargoId(@RequestBody InvoiceDto invoiceDto, @RequestParam("cargoId") Long cargoId) {
        if (invoiceDto == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Invoice invoice = invoiceService.createAndSave(invoiceDto);
        cargoService.updateInvoice(invoice, Long.valueOf(cargoId));

        return new ResponseEntity(HttpStatus.OK);
    }
}
