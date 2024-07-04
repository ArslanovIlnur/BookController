package org.app.menu;

import org.app.writers.BookWriter;
import org.app.commands.AppCommands;
import org.app.service.BookService;
import org.app.service.ReservationService;
import org.app.service.UserService;
import org.app.writers.ReservationWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {
    private ReservationWriter reservationWriter;
    private UserService userService;
    private BookService bookService;
    private BookWriter bookWriter;
    private ReservationService reservationService;

    @Autowired
    public MainMenu(ReservationWriter reservationWriter, UserService userService, BookService bookService, BookWriter bookWriter, ReservationService reservationService) {
        this.reservationWriter = reservationWriter;
        this.userService = userService;
        this.bookService = bookService;
        this.bookWriter = bookWriter;
        this.reservationService = reservationService;
    }

    @Bean
    public void mainMenuRun(){
        UserMenu userMenu = new UserMenu(reservationWriter,bookService, bookWriter, userService, reservationService);
        MainMenu mainMenu = new MainMenu(reservationWriter, userService,bookService, bookWriter, reservationService);
        BooksMenu booksMenu = new BooksMenu(mainMenu,bookService, bookWriter);
        ReservationMenu reservationMenu = new ReservationMenu(mainMenu,reservationWriter,reservationService);

        Scanner scanner = AppCommands.scanner();

        System.out.println("+----------------------+");
        System.out.println("|     Главное меню     |");
        System.out.println("+----------------------+");
        System.out.println("1. Меню пользователей");
        System.out.println("2. Меню книг");
        System.out.println("3. Меню броней");
        System.out.println("4. Выход");
        System.out.println("+----------------------+");

        int command = scanner.nextInt();
        if (command == 1){
            userMenu.userMenuRun();
        } else if (command == 2) {
            booksMenu.bookMenu();
        } else if (command == 3) {
            reservationMenu.reservationMenuRun();
        } else if (command == 4) {
            AppCommands.exitFromApp();
        } else {
            System.out.println("Неверная команда");
            mainMenuRun();
        }
    }
}
