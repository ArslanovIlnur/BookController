package org.app.service;

import org.app.commands.AppCommands;
import org.app.entities.Book;
import org.app.entities.Reservation;
import org.app.entities.User;
import org.app.repositories.BookRepository;
import org.app.repositories.ReservationRepository;
import org.app.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ReservationService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;

    Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(UserRepository userRepository, BookRepository bookRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
    }

    @Bean
    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }

    @Bean
    public Reservation createReservation(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите ID книги:");
        Long bookId = scanner.nextLong();
        Book bookForReservation = bookRepository.getById(bookId);
        if (!bookForReservation.isInStock()){
            logger.error((char)27 + "[31m" + "Книга занята"+ (char)27 + "[0m");
            return null;
        } else {
            System.out.println("Введите ID пользователя:");
            Long userId = scanner.nextLong();

            User userForReservation = userRepository.getById(userId);

            bookForReservation.setInStock(false);

            Reservation reservation = new Reservation();
            reservation.setUser(userForReservation);
            reservation.setBook(bookForReservation);

            bookRepository.save(bookForReservation);
            reservationRepository.save(reservation);
            return reservation;
        }
    }

    @Bean
    public void deleteReservation(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите ID брони:");
        Long reservationID = scanner.nextLong();

        Reservation reservation = reservationRepository.getById(reservationID);
        Book book = reservation.getBook();
        book.setInStock(true);

        bookRepository.save(book);
        reservationRepository.deleteById(reservationID);
        logger.info("Удалено");
    }

    @Bean
    public Reservation getReservationByBook(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите ID книги");
        Long id = scanner.nextLong();
        Book book = bookRepository.getById(id);

        Reservation reservation = reservationRepository.getByBook(book);
        return reservation;
    }

    @Bean
    public List<Reservation> getReservationsByUserId(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("Введите ID пользователя");
        Long id = scanner.nextLong();

        User user = userRepository.getById(id);

        return reservationRepository.getByUser(user);
    }
}
