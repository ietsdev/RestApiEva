package com.nttdata.evaluacion.restapi.service;
import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.representation.Respuesta;
import com.nttdata.evaluacion.util.BusinessException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IUsuarioService  {
    List<Usuario> getUsuarios(String email);
    Respuesta<Usuario> crearUsuario(Usuario usuario) throws BusinessException;
    // UserDetails cargarUsuarioParaToken(Usuario usuario);
}
