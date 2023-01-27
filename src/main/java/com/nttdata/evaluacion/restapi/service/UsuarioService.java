package com.nttdata.evaluacion.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.repositories.UsuarioRepository;
import com.nttdata.evaluacion.restapi.representation.Respuesta;
import com.nttdata.evaluacion.restapi.security.JwtTokenUtil;

@Service
public class UsuarioService implements IUsuarioService{
    
    public String EJECUTADO_OK = "Ejecutado Correctamente";
    public String CORREO_REGISTRADO = "Correo electronico ya utilizado.";
    public String CORREO_INVALIDO = "Correo electronico no es valido.";
    public String CLAVE_INVALIDA = "La contraseña no es valida (Una Mayuscula, letras minúsculas, y dos numeros).";

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<List<Usuario>> getUsuarios(String email) {
        
        List<Usuario> usuarios = new ArrayList<Usuario>();

        if (email == null)
            usuarioRepository.findAll().forEach(usuarios::add);
        else
            usuarioRepository.findAllByEmail(email).forEach(usuarios::add);

        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Respuesta<Usuario>> crearUsuario(Usuario usuario) {
        if(usuario.getName() == null && usuario.getEmail() == null && usuario.getPassword() == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);        

        if(!ValidarCorreo(usuario.getEmail()))
            return new ResponseEntity<>(new Respuesta<Usuario>(CORREO_INVALIDO, null), HttpStatus.BAD_REQUEST);

        if(!ValidarClave(usuario.getPassword()))
            return new ResponseEntity<>(new Respuesta<Usuario>(CLAVE_INVALIDA, null), HttpStatus.BAD_REQUEST);

        Usuario existente = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);
        if(existente != null)
            return new ResponseEntity<>(new Respuesta<Usuario>(CORREO_REGISTRADO, null), HttpStatus.BAD_REQUEST);

        Usuario nuevoUsuario = new Usuario(usuario.getName(), usuario.getEmail(), usuario.getPassword());
        nuevoUsuario.setPhones(usuario.getPhones());
        nuevoUsuario.setToken(jwtTokenUtil.generateToken(nuevoUsuario.getName(),nuevoUsuario.getEmail()));
        Usuario _usuario = usuarioRepository.saveAndFlush(nuevoUsuario);

        return new ResponseEntity<>(new Respuesta<Usuario>(EJECUTADO_OK, _usuario), HttpStatus.CREATED);
    }

    private boolean ValidarCorreo(String correo)
    {
        Pattern pattern = Pattern
        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);
        return (mather.find() == true);
    }

    private boolean ValidarClave(String clave)
    {
        Pattern pattern = Pattern
        .compile("^[A-Z]{1}[a-z]+[0-9]{2}$");
        // .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        //         + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(clave);
        return (mather.find() == true);
    }

    // @Override
    // public UserDetails cargarUsuarioParaToken(Usuario usuario) {
    //     return new User(usuario.getName(), usuario.getPassword(),
	// 				new ArrayList<>());
    // }
    
}

































