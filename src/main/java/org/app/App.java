package org.app;

import org.app.writers.BookWriter;
import org.app.menu.MainMenu;
import org.app.service.BookService;
import org.app.service.ReservationService;
import org.app.service.UserService;
import org.app.writers.ReservationWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class App {
    private ReservationWriter reservationWriter;
    private ReservationService reservationService;
    private UserService userService;
    private BookService bookService;
    private BookWriter bookWriter;

    @Autowired
    public App(ReservationWriter reservationWriter, ReservationService reservationService, UserService userService, BookService bookService, BookWriter bookWriter) {
        this.reservationWriter = reservationWriter;
        this.reservationService = reservationService;
        this.userService = userService;
        this.bookService = bookService;
        this.bookWriter = bookWriter;
    }

    @Bean
    public void run(){
        MainMenu mainMenu = new MainMenu(reservationWriter, userService, bookService, bookWriter, reservationService);
        mainMenu.mainMenuRun();
    }
}
