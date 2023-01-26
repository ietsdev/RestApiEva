package com.nttdata.evaluacion.restapi.controller;

import java.util.ArrayList;
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

import com.nttdata.evaluacion.restapi.model.Telefono;
import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.repositories.TelefonoRepository;
import com.nttdata.evaluacion.restapi.repositories.UsuarioRepository;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

	@Autowired
    TelefonoRepository telefonoRepository;

    @GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required = false) String email) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (email == null)
                usuarioRepository.findAll().forEach(usuarios::add);
			else
                usuarioRepository.findByEmail(email).forEach(usuarios::add);

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/usuarios")
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario nuevoUsuario = new Usuario(usuario.getName(), usuario.getEmail(), usuario.getPassword());
			nuevoUsuario.setPhones(usuario.getPhones());
			Usuario _usuario = usuarioRepository.save(nuevoUsuario);

			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
