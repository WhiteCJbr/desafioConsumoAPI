package com.desafio.desafioSpringAPI;

import com.desafio.desafioSpringAPI.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioSpringApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibePrincipal();
	}
}
