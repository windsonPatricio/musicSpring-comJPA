package br.com.alura.springmusic;

import br.com.alura.springmusic.principal.Principal;
import br.com.alura.springmusic.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringmusicApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SpringmusicApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		 Principal principal = new Principal(repositorio);
		 principal.exibeMenu();

	}
}
