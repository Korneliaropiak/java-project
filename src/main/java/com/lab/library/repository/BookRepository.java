package com.lab.library.repository;

import com.lab.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByRentedBy_Id(Long userId);
    List<Book> findByRentedByIsNull();
}
