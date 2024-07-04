package org.app.menu;

import org.app.writers.BookWriter;
import org.app.commands.AppCommands;
import org.app.writers.ReservationWriter;
import org.app.writers.UserWriter;
import org.app.service.BookService;
import org.app.service.ReservationService;
import org.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserMenu {
    private final ReservationWriter reservationWriter;
    private final BookService bookService;
    private final BookWriter bookWriter;
    private final UserService userService;
    private final ReservationService reservationService;

    @Autowired
    public UserMenu(ReservationWriter reservationWriter, BookService bookService, BookWriter bookWriter, UserService userService, ReservationService reservationService) {
        this.reservationWriter = reservationWriter;
        this.bookService = bookService;
        this.bookWriter = bookWriter;
        this.userService = userService;
        this.reservationService = reservationService;
    }


    @Bean
    public void userMenuRun(){
        UserWriter userWriter = new UserWriter(userService);
        MainMenu mainMenu = new MainMenu(reservationWriter,userService, bookService, bookWriter, reservationService);
        Scanner scanner = AppCommands.scanner();

        System.out.println("+---------------------------------+");
        System.out.println("|           ������������          |");
        System.out.println("+---------------------------------+");
        System.out.println("1. �������� ���� �������������");
        System.out.println("2. �������� ������������");
        System.out.println("3. ����� ������������");
        System.out.println("4. ������� ������������");
        System.out.println("5. �������� ��������� ������������");
        System.out.println("6. ����� ����� ������������");
        System.out.println("7. ������� ����");
        System.out.println("8. �����");
        System.out.println("+----------------------------------+");


        int command = scanner.nextInt();

        if (command == 1){
            userWriter.writeAllUsers();
            userMenuRun();
        } else if (command == 2) {
            userService.createUser();
            userMenuRun();
        } else if (command == 3) {
            userWriter.writeUserByName(userService.getUserByName());
            userMenuRun();
        } else if (command == 4) {
            userService.deleteUser();
            userMenuRun();
        } else if (command == 5) {
            userService.editUser();
            userMenuRun();
        } else if (command == 6) {
            reservationWriter.writeReservations(reservationService.getReservationsByUserId());
            userMenuRun();
        } else if (command == 7) {
            mainMenu.mainMenuRun();
        } else if (command == 8) {
            AppCommands.exitFromApp();
        } else {
            System.out.println("�������� �������");
            userMenuRun();
        }
    }
}
