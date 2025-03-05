package fr.dawan.spring_rest_api;

import fr.dawan.spring_rest_api.entities.Product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


@SpringBootApplication
public class SpringRestApiApplication implements CommandLineRunner {

	@Value("${java.home}")
	private String javaHome;


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public PasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}

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

		Contact c = new Contact();
		c.setFirstName("Fname");
		c.setLastName("Lname");
		ModelMapper mapper = new ModelMapper();

		mapper.typeMap(Contact.class, ContactDto.class)
				.addMappings(m -> {
			m.map(src -> src.getFirstName(),ContactDto::setNom);
			m.map(src -> src.getLastName(),ContactDto::setPrenom);

		});


		ContactDto dto = mapper.map(c, ContactDto.class);
		System.out.println(dto);



	}
}

@Getter
@Setter
class Contact{
	private String firstName;
	private String lastName;
}


@Getter
@Setter
@ToString
class ContactDto{
	private String nom;
	private String prenom;
}