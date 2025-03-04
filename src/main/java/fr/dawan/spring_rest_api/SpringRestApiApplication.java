package fr.dawan.spring_rest_api;

import fr.dawan.spring_rest_api.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


@SpringBootApplication
public class SpringRestApiApplication implements CommandLineRunner {

	@Value("${java.home}")
	private String javaHome;


	/*
	Accéder à l'environnement de Spring
	 */
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(javaHome);
		System.out.println(environment.getProperty("java.home"));

		//Ecriture dans un fichier.properties
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream("login.properties");
		p.put("user", "Admin");
		p.put("password", "@@dsdqsd");
		p.store(fos,"Params de connexion");
		fos.close();

		//Lecture d'un fichier.properties
		FileInputStream fis = new FileInputStream("login.properties");
		p.load(fis);
		System.out.println(p.getProperty("user"));
		System.out.println(p.getProperty("password"));
		fis.close();

		Product prod = new Product();
		prod.setDescription("pc");
		System.out.println(prod.getDescription());
	}
}
