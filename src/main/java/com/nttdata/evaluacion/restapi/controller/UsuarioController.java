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
import com.nttdata.evaluacion.util.BusinessException;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
    IUsuarioService usuarioService;

    @GetMapping("/usuarios")
	public ResponseEntity<Respuesta<List<Usuario>>> getUsuarios(@RequestParam(required = false) String email) {
		try {
			Respuesta<List<Usuario>> respuesta = new Respuesta<List<Usuario>>("", usuarioService.getUsuarios(email));
			return new ResponseEntity<Respuesta<List<Usuario>>>(respuesta,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/usuarios")
	public ResponseEntity<Respuesta<Usuario>> crearUsuario(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<Respuesta<Usuario>>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
		} 
		catch (BusinessException ex) {
			return new ResponseEntity<Respuesta<Usuario>>(new Respuesta<Usuario>(ex.getMessage(),null), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
