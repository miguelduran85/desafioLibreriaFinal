package com.aluracursos.desafio_libreria;

import com.aluracursos.desafio_libreria.pricipal.Principal;
import com.aluracursos.desafio_libreria.repository.AutorRepository;
import com.aluracursos.desafio_libreria.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLibreriaApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository librosRepository;
	private AutorRepository autorRepository;

	public DesafioLibreriaApplication(LibrosRepository libroRepository, AutorRepository autorRepository) {

		this.librosRepository = libroRepository;
		this.autorRepository = autorRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(DesafioLibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository, autorRepository);
		principal.Elmenu();

	}

}
