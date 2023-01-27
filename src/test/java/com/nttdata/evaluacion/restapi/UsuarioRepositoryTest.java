package com.nttdata.evaluacion.restapi;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.nttdata.evaluacion.restapi.model.Usuario;
import com.nttdata.evaluacion.restapi.repositories.UsuarioRepository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UsuarioRepositoryTest {
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test	
	void whengetUsuarios() {
		 //given
		 Usuario usuario = new Usuario("Juan Rodriguez","juan@rodriguez.org","hunter2");
		 entityManager.persist(usuario);
		 entityManager.flush();
		 Usuario usuario2 = new Usuario("Ismael Tapia","itapiasa@rodriguez.org","Passok01");
		 entityManager.persist(usuario2);
		 entityManager.flush();

        //when
        List<Usuario> usuarios = usuarioRepository.findAllByEmail("juan@rodriguez.org");

        //then
        assertThat(usuarios.size()).isEqualTo(1);
		 
	}
}
