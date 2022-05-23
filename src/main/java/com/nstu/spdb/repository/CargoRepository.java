package com.nstu.spdb.repository;

import com.nstu.spdb.entity.Cargo;
import com.nstu.spdb.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    @Query(value = "select * from order_cargo co where co.id = (select c.cargos from cargo c where c.id = ?1)", nativeQuery = true)
    Order getOrderByCargoId(Long orderId);

    List<Cargo> getAllByInvoiceNull();
}
