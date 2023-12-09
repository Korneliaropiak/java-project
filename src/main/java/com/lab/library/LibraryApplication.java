package com.lab.library;

import com.lab.library.domain.Book;
import com.lab.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	@Autowired
	public LibraryApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.save(new Book("Spring in Action", "Craig Walls", true));
		bookRepository.save(new Book("Clean Code", "Robert C. Martin", true));
		bookRepository.save(new Book("Design Patterns", "Erich Gamma", true));
	}
}
