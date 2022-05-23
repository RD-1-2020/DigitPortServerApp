package com.nstu.spdb.service;

import com.nstu.spdb.dto.CargoDto;
import com.nstu.spdb.entity.Cargo;
import com.nstu.spdb.entity.Invoice;
import com.nstu.spdb.repository.CargoRepository;
import com.nstu.spdb.repository.OrderRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {
    private static final Logger logger = LoggerFactory.getLogger(CargoService.class);

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderService orderService;

    public Cargo createFromDto(CargoDto cargoDto) {
        if (cargoDto == null) {
            return null;
        }

        Cargo cargo = new Cargo();
        cargo.setWeight(cargoDto.getWeight());
        cargo.setTitle(cargoDto.getTitle());
        cargo.setId(cargoDto.getId());
        cargo.setInvoice(invoiceService.createFromDto(cargoDto.getInvoice()));

        return cargo;
    }

    public List<Cargo> createFromDto(List<CargoDto> cargoDtos) {
        if (cargoDtos == null) {
            return null;
        }

        List<Cargo> cargoList = new ArrayList<>();
        cargoDtos.forEach(cargoDto -> {
            cargoList.add(createFromDto(cargoDto));
        });

        return cargoList;
    }

    @Nullable
    public Cargo createCargo(String tittle, Long weight) {
        if (StringUtils.isBlank(tittle) && weight <= 0) {
            return null;
        }

        logger.info("Create cargo with '{}' weight and title is '{}'", weight, tittle);
        Cargo cargo = new Cargo();
        cargo.setTitle(tittle);
        cargo.setWeight(weight);

        return cargoRepository.save(cargo);
    }

    @Nullable
    public List<Cargo> createCargos(List<Cargo> cargos) {
        if (cargos == null) {
            return null;
        }

        List<Cargo> savedCargos = new ArrayList<>();
        for (Cargo cargo : cargos) {
            if (StringUtils.isBlank(cargo.getTitle()) && cargo.getWeight() > 0) {
                return null;
            }

            logger.info("Create cargo with '{}' weight and title is '{}'", cargo.getWeight(), cargo.getTitle());
            savedCargos.add(cargoRepository.save(cargo));
        }

        return savedCargos;
    }

    public List<CargoDto> getAllCargosWithoutInvoice() {
        final List<Cargo> invoiceNotNull = cargoRepository.getAllByInvoiceNull();
        if (invoiceNotNull == null) {
            return null;
        }

        List<CargoDto> cargoDtos = new ArrayList<>();
        invoiceNotNull.forEach(cargo -> {
            cargoDtos.add(new CargoDto(cargo));
        });

        return cargoDtos;
    }

    public void updateInvoice(Invoice invoice, Long cargoId) {
        final Cargo cargo = cargoRepository.getOne(cargoId);
        if (cargo == null) {
            return;
        }

        cargo.setInvoice(invoice);
        cargoRepository.save(cargo);
    }
}
