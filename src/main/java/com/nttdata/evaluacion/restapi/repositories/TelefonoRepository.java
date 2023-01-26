package com.nttdata.evaluacion.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.evaluacion.restapi.model.Telefono;

public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
    
}