package org.app.commands;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AppCommands {
    @Bean
    public static void exitFromApp(){
        System.exit(0);
    }

    @Bean
    public static Scanner scanner(){
        return new Scanner(System.in);
    }
}
