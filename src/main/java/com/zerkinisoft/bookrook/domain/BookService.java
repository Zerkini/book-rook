package com.zerkinisoft.bookrook.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Integer addBook(Book book) {
        return this.bookRepository.save(book).id;
    }

    public Book getBook(Integer id) {
        return this.bookRepository.findById(id).orElse(null);
    }

}
