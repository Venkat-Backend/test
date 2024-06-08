package com.spantag.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spantag.springsecurity.entity.Role;
import com.spantag.springsecurity.entity.User;
import com.spantag.springsecurity.repository.UserRepository;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	
	public void run(String... args) {
		
		User superAdminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == superAdminAccount) {
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setSecondname("Brokify");
			user.setRole(Role.ADMIN);
			System.out.println(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin@123$"));
			userRepository.save(user);
		}
		
	}

}
