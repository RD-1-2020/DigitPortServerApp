package com.nstu.spdb.repository;

import com.nstu.spdb.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> getClientsByFullName(String fullName);
}
