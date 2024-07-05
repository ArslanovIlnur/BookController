package org.app.service;

import org.app.entities.Book;
import org.app.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    private BookRepository bookRepository;

    @Autowired
    BookServiceTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void findAllBooks() {
        BookService bookService = new BookService(bookRepository);
        assertEquals(0, bookService.findAllBooks().size());

        Book book = new Book();
        book.setName("test");
        book.setAuthor("test");
        book.setInStock(true);
        entityManager.persist(book);
        entityManager.flush();

        assertNotEquals(0, bookService.findAllBooks().size());
    }

    @Test
    void getBookByName() throws IOException {
        BookService bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setName("test");
        book.setAuthor("test");
        book.setInStock(true);
        entityManager.persist(book);
        entityManager.flush();

        String name = "test";
        InputStream inputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));

        System.setIn(inputStream);

        List<Book> books = bookService.getBookByName();
        assertNotNull(books);
    }

    @Test
    void deleteBookById() {
        BookService bookService = new BookService(bookRepository);

        Book book = new Book();
        book.setName("test");
        book.setAuthor("test");
        book.setInStock(true);
        entityManager.persist(book);
        entityManager.flush();

        assertNotEquals(0, bookRepository.getBookByName("test"));

        Long id = book.getId();
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(id).getBytes(StandardCharsets.UTF_8));

        System.setIn(inputStream);
        bookService.deleteBookById();
        assertEquals(0, bookRepository.getBookByName("test").size());
    }
}