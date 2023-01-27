package com.nttdata.evaluacion.restapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.representation.Respuesta;
import com.nttdata.evaluacion.restapi.service.IUsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
    IUsuarioService usuarioService;

    @GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required = false) String email) {
		try {
			return usuarioService.getUsuarios(email);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/usuarios")
	public ResponseEntity<Respuesta<Usuario>> crearUsuario(@RequestBody Usuario usuario) {
		try {
			return usuarioService.crearUsuario(usuario);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
