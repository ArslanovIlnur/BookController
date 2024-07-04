package org.app.service;

import org.app.commands.AppCommands;
import org.app.entities.User;
import org.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Bean
    public User createUser(){
        User user = new User();
        Scanner scanner = AppCommands.scanner();

        System.out.println("¬ведите им€ пользовател€:");
        String name = scanner.nextLine();

        user.setName(name);
        userRepository.save(user);

        return user;
    }

    @Bean
    public List<User> getUserByName(){
        List<User> users = new ArrayList<>();
        Scanner scanner = AppCommands.scanner();

        System.out.println("¬ведите им€ пользовател€");
        String name = scanner.nextLine();

        users = userRepository.getUserByName(name);

        if (users.isEmpty()){
            return null;
        } else {
            return users;
        }
    }

    @Bean
    public void deleteUser(){
        Scanner scanner = AppCommands.scanner();

        System.out.println("¬ведите ID пользовател€ дл€ удалени€");
        Long id = scanner.nextLong();

        userRepository.deleteById(id);
        System.out.println("”далено");
    }

    @Bean
    public User editUser(){
        User user;
        Scanner scanner = AppCommands.scanner();

        System.out.println("¬ведите ID пользовател€ дл€ изменени€:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            user = userRepository.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("¬ведите новое им€:");
        String name = scanner.nextLine();

        user.setName(name);
        userRepository.save(user);

        return user;
    }
}
