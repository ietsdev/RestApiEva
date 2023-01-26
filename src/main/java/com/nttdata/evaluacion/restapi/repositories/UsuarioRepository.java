package com.nttdata.evaluacion.restapi.repositories;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.evaluacion.restapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    List<Usuario> findByEmail(String title);
}
