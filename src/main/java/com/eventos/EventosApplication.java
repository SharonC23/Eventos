package com.eventos;

import com.eventos.config.enums.RolUsuario;
import com.eventos.entity.CategoriaEvento;
import com.eventos.entity.Evento;
import com.eventos.entity.Imagenes;
import com.eventos.entity.Usuario;
import com.eventos.repository.CategoriaRepository;
import com.eventos.repository.EventoRepository;
import com.eventos.repository.ImagenRepository;
import com.eventos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class
EventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventosApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, ImagenRepository imagenRepository, EventoRepository eventoRepository, CategoriaRepository categoriaRepository, PasswordEncoder passwordEncoder){
        return args -> {
            // ADMIN

            if(usuarioRepository.findByEmail("sharon@gmail.com").isEmpty()){
                Usuario usuario = new Usuario();

                usuario.setCedula("1234567890");
                usuario.setRol(RolUsuario.ADMINISTRADOR);
                usuario.setNombreCompleto("Sharon Yirley Carrillo Virguez");
                usuario.setPassword(passwordEncoder.encode("sharon1234"));
                usuario.setEmail("sharon@gmail.com");

                usuarioRepository.save(usuario);
            }

        };
    }
}
