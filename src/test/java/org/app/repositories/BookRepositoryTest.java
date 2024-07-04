package org.app.repositories;

import org.app.entities.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void getBookByName() {
        Book book = new Book();
        book.setName("test");
        book.setAuthor("test");
        book.setInStock(true);

        entityManager.persist(book);
        entityManager.flush();

        List<Book> testBooks = bookRepository.getBookByName("test");
        assertEquals(testBooks.get(0).getName(), book.getName());
    }
}