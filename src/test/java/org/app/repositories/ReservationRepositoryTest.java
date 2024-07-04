package org.app.repositories;

import org.app.entities.Book;
import org.app.entities.Reservation;
import org.app.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void getByBook() {
        Reservation reservation = new Reservation();

        Book book = new Book();
        book.setName("Test book");
        book.setAuthor("Test book");

        User user = new User();
        user.setName("Test User");

        entityManager.persist(book);
        entityManager.flush();

        entityManager.persist(user);
        entityManager.flush();

        reservation.setBook(book);
        reservation.setUser(user);

        entityManager.persist(reservation);
        entityManager.flush();

        Reservation testReservation = reservationRepository.getByBook(book);

        assertEquals(reservation, testReservation);
    }

    @Test
    void getByUser() {
        Reservation reservation = new Reservation();

        Book book = new Book();
        book.setName("Test book");
        book.setAuthor("Test book");

        User user = new User();
        user.setName("Test User");

        entityManager.persist(book);
        entityManager.flush();

        entityManager.persist(user);
        entityManager.flush();

        reservation.setBook(book);
        reservation.setUser(user);

        entityManager.persist(reservation);
        entityManager.flush();

        List<Reservation> testReservations = reservationRepository.getByUser(user);

        assertEquals(testReservations.get(0).getUser(), user);
    }
}