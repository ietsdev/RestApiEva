package com.nttdata.evaluacion.restapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.evaluacion.restapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByEmail(String title);
}
