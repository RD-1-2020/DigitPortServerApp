package com.nstu.spdb.repository;

import com.nstu.spdb.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Cargo findByTitle(String title);
}
