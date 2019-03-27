package com.github.guilherme.crudprova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.guilherme.crudprova.model.LimitesCliente;

@Repository
public interface LimitesClienteRepository extends JpaRepository<LimitesCliente, Long> {

    LimitesCliente findByName(String name);

}
