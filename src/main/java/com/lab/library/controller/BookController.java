package com.lab.library.controller;

import com.lab.library.domain.Book;
import com.lab.library.domain.User;
import com.lab.library.repository.BookRepository;
import com.lab.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Autowired
    public BookController(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @GetMapping("/books/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            return "edit_book";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            bookRepository.save(existingBook);
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books_list";
    }

    @GetMapping("/books/rent/{id}")
    public String rentBook(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User currentUser = userService.findUserByEmail(email);

        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setRentedBy(currentUser);
            bookRepository.save(existingBook);
        }
        return "redirect:/rented-books";
    }

    @GetMapping("/books/return/{id}")
    public String returnBook(@PathVariable Long id) {
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setRentedBy(null);
            bookRepository.save(existingBook);
        }
        return "redirect:/books";
    }

    @GetMapping("/rented-books")
    public String getMyRentedBooks(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User currentUser = userService.findUserByEmail(email);
        model.addAttribute("rentedBooks", currentUser.getRentedBooks());
        model.addAttribute("books", bookRepository.findByRentedByIsNull());

        return "rented_books";
    }
}


