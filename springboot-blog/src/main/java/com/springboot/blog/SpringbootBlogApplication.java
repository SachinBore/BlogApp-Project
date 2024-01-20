package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.RoleRepository;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App REST APIs",
				description = "Srping Boot Blog App REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Sachin",
						email = "sachinboreofficial@gmail.com",
						url = "https://www.linkedin.com/in/sachinbore"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App Documentation",
				url = "https://github.com/SachinBore"
		)
)
public class SpringbootBlogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Role adminRole = new Role();
		adminRole.setId((long) 1);
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setId((long) 2);
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
	}

}
