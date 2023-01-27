package com.nttdata.evaluacion.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.repositories.UsuarioRepository;
import com.nttdata.evaluacion.restapi.representation.Respuesta;
import com.nttdata.evaluacion.restapi.security.JwtTokenUtil;
import com.nttdata.evaluacion.util.BusinessException;


@Service
public class UsuarioService implements IUsuarioService{
    
    public String CORREO_REGISTRADO = "Correo electronico ya esta utilizado.";
    public String CORREO_INVALIDO = "El correo electronico no es valido.";
    public String CLAVE_INVALIDA = "La contraseña no es valida (Una Mayuscula, letras minúsculas, y dos numeros).";
    public String DATOS_INVALIDOS = "Falta uno o mas campos requeridos.";
	public String EJECUTADO_OK = "Ejecutado Correctamente";

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Usuario> getUsuarios(String email) {
        
        List<Usuario> usuarios = new ArrayList<Usuario>();

        if (email == null)
            usuarioRepository.findAll().forEach(usuarios::add);
        else
            usuarioRepository.findAllByEmail(email).forEach(usuarios::add);

        return usuarios;
    }

    @Override
    public Respuesta<Usuario> crearUsuario(Usuario usuario) throws BusinessException {
        System.out.print(usuario.getName());
        if(usuario.getName() == null || usuario.getEmail() == null || usuario.getPassword() == null)
            throw new BusinessException(DATOS_INVALIDOS);           

        if(!ValidarCorreo(usuario.getEmail()))
            throw new BusinessException(CORREO_INVALIDO);           

        if(!ValidarClave(usuario.getPassword()))
            throw new BusinessException(CLAVE_INVALIDA);           

        Usuario existente = usuarioRepository.findByEmail(usuario.getEmail()).orElse(null);
        if(existente != null)
            throw new BusinessException(CORREO_REGISTRADO);            

        Usuario nuevoUsuario = new Usuario(usuario.getName(), usuario.getEmail(), usuario.getPassword());
        nuevoUsuario.setPhones(usuario.getPhones());
        nuevoUsuario.setToken(jwtTokenUtil.generateToken(nuevoUsuario.getName(),nuevoUsuario.getEmail()));
        Usuario _usuario = usuarioRepository.saveAndFlush(nuevoUsuario);

        return new Respuesta<Usuario>(EJECUTADO_OK, _usuario);
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

































