package org.app.service;

import org.app.commands.AppCommands;
import org.app.entities.Book;
import org.app.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Bean
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @Bean
    public Book createBook(){
        Book book = new Book();

        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите название книги");
        String name = scanner.nextLine();

        System.out.println("Введите автора");
        String author = scanner.nextLine();

        book.setName(name);
        book.setAuthor(author);
        book.setInStock(true);

        bookRepository.save(book);

        return book;
    }

    @Bean
    public List<Book> getBookByName(){
        List<Book> books = new ArrayList<>();

        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите название книги");
        String name = scanner.nextLine();

        books = bookRepository.getBookByName(name);
        if (books.isEmpty()){
            return null;
        } else {
            return books;
        }
    }

    @Bean
    public void deleteBookById(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите ID книги");
        Long id = scanner.nextLong();

        bookRepository.deleteById(id);
        logger.info("Удаление");
    }
}
