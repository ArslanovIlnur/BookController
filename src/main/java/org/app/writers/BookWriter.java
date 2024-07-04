package org.app.writers;

import org.app.entities.Book;
import org.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWriter {
    private final BookService bookService;

    @Autowired
    public BookWriter(BookService bookService) {
        this.bookService = bookService;
    }

    @Bean
    public void writeAllBooks(){
        List<Book> books = bookService.findAllBooks();
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|                         ������ ����                       |");
        System.out.println("|---ID----|---��������-----|-------�����------|--� �������--|");
        books.stream().forEach(book -> System.out.println(book.getId() + " | "
        + book.getName() + " | " + book.getAuthor() + " | " + book.isInStock()));
        System.out.println("-------------------------------------------------------------");
    }

    @Bean
    public void writeBooksByName(List<Book> books) {
        if (books == null) {
            System.out.println("����� �� �������");
        } else {
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("|                         ������ ����                       |");
            System.out.println("|---ID----|---��������-----|-------�����------|--� �������--|");
            books.stream().forEach(book -> System.out.println(book.getId() + " | "
                    + book.getName() + " | " + book.getAuthor() + " | " + book.isInStock()));
            System.out.println("-------------------------------------------------------------");
        }
    }
}
