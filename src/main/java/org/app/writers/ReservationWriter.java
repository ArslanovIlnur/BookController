package org.app.writers;

import org.app.entities.Reservation;
import org.app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationWriter {
    private final ReservationService reservationService;

    @Autowired
    public ReservationWriter(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Bean
    public void writeAllReservations(){
        List<Reservation> reservations = reservationService.findAllReservations();

        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                                 �����                              |");
        System.out.println("+--------------------------------------------------------------------+");
        reservations.stream().forEach(reservation -> System.out.println(
                "����� �����: " + reservation.getId() + "\n" +
                        "������������: " + reservation.getUser().getId() + " | " + reservation.getUser().getName() + "\n" +
                        "�����: " + reservation.getBook().getName() + " | " + reservation.getBook().getAuthor() + "\n" +
                        "======================================================================"

        ));
    }

    @Bean
    public void writeReservation(Reservation reservation){
        System.out.println("\n����� �����: " + reservation.getId() + "\n" +
                "�����: " + reservation.getBook().getName() + " | " + reservation.getBook().getAuthor() + "\n" +
                "������������: " + reservation.getUser().getId() + " | " + reservation.getUser().getName() + "\n");
    }

    @Bean
    public void writeReservations(List<Reservation> reservations){
        reservations.stream().forEach(reservation ->
                System.out.println("\n����� �����: " + reservation.getId() + "\n" +
                        "�����: " + reservation.getBook().getName() + " | " + reservation.getBook().getAuthor() + "\n" +
                        "������������: " + reservation.getUser().getId() + " | " + reservation.getUser().getName() + "\n")
                );
    }
}
