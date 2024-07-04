package org.app.repositories;

import org.app.entities.Book;
import org.app.entities.Reservation;
import org.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
       Reservation getByBook(Book book);
       List<Reservation> getByUser(User user);
 }
