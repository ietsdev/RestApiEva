package com.nttdata.evaluacion.restapi.service;
import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.representation.Respuesta;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Service
public interface IUsuarioService  {
    ResponseEntity<List<Usuario>> getUsuarios(String email);
    ResponseEntity<Respuesta<Usuario>> crearUsuario(Usuario usuario);
    // UserDetails cargarUsuarioParaToken(Usuario usuario);
}
