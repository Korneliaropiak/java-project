package com.lab.library;

import com.lab.library.domain.Book;
import com.lab.library.domain.Role;
import com.lab.library.domain.User;
import com.lab.library.repository.BookRepository;
import com.lab.library.repository.RoleRepository;
import com.lab.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;


	@Autowired
	public LibraryApplication(BookRepository bookRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.save(new Book("Spring in Action", "Craig Walls"));
		bookRepository.save(new Book("Clean Code", "Robert C. Martin"));
		bookRepository.save(new Book("Design Patterns", "Erich Gamma"));
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		User admin = new User();
		admin.setName("Kornelia R");
		admin.setEmail("k@wp.pl");
		System.out.println(passwordEncoder.encode("123"));
		admin.setPassword(passwordEncoder.encode("123"));
		admin.setRoles(Arrays.asList(role));
		userRepository.save(admin);
	}
}
