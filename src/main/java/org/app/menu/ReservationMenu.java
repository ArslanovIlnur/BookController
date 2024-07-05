package org.app.menu;

import org.app.commands.AppCommands;
import org.app.service.ReservationService;
import org.app.commands.writers.ReservationWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ReservationMenu {
    private final MainMenu mainMenu;
    private final ReservationWriter reservationWriter;
    private final ReservationService reservationService;

    @Autowired
    public ReservationMenu(MainMenu mainMenu, ReservationWriter reservationWriter, ReservationService reservationService) {
        this.mainMenu = mainMenu;
        this.reservationWriter = reservationWriter;
        this.reservationService = reservationService;
    }

    @Bean
    public void reservationMenuRun(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("+-----------------------+");
        System.out.println("|          Брони        |");
        System.out.println("+-----------------------+");
        System.out.println("1. Вывести все брони");
        System.out.println("2. Создать бронь");
        System.out.println("3. Удалить бронь");
        System.out.println("4. Найти бронь по книге");
        System.out.println("5. Главное меню");
        System.out.println("6. Выход");
        System.out.println("+-----------------------+");


        int command = scanner.nextInt();

        if (command == 1){
            reservationWriter.writeAllReservations();
            reservationMenuRun();
        } else if (command == 2) {
            reservationService.createReservation();
            reservationMenuRun();
        } else if (command ==3) {
            reservationService.deleteReservation();
            reservationMenuRun();
        } else if (command == 4) {
            reservationWriter.writeReservation(reservationService.getReservationByBook());
            reservationMenuRun();
        } else if (command == 5) {
            mainMenu.mainMenuRun();
        } else if (command == 6) {
            AppCommands.exitFromApp();
        } else {
            System.out.println("Неверная команда");
            reservationMenuRun();
        }
    }
}
