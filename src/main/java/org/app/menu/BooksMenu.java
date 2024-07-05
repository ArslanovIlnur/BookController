package org.app.menu;

import org.app.commands.AppCommands;
import org.app.commands.writers.BookWriter;
import org.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BooksMenu {
    private final MainMenu mainMenu;
    private final BookService bookService;
    private final BookWriter bookWriter;

    @Autowired
    public BooksMenu(MainMenu mainMenu, BookService bookService, BookWriter bookWriter) {
        this.mainMenu = mainMenu;
        this.bookService = bookService;
        this.bookWriter = bookWriter;
    }

    @Bean
    public void bookMenu(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("-----------------");
        System.out.println("  Книжное меню  ");
        System.out.println("-----------------");
        System.out.println("1. Список книг");
        System.out.println("2. Добавить книгу");
        System.out.println("3. Найти книгу");
        System.out.println("4. Удалить книгу");
        System.out.println("5. Главное меню");
        System.out.println("6. Выход");
        System.out.println("-----------------");

        int command = scanner.nextInt();

        if (command == 1){
            bookWriter.writeAllBooks();
            bookMenu();
        } else if (command == 2) {
            bookService.createBook();
            bookMenu();
        } else if (command ==3) {
            bookWriter.writeBooksByName(bookService.getBookByName());
            bookMenu();
        } else if (command == 4){
            bookService.deleteBookById();
            bookMenu();
        } else if (command == 5) {
            mainMenu.mainMenuRun();
        } else if (command == 6) {
            AppCommands.exitFromApp();
        } else {
            System.out.println("Неверная команда");
            bookMenu();
        }
    }
}
