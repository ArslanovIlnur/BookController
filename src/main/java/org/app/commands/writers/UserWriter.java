package org.app.commands.writers;

import org.app.entities.User;
import org.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserWriter {
    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserWriter.class);

    @Autowired
    public UserWriter(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public void writeAllUsers(){
        logger.info("Вывод всех пользователей");

        List<User> users = userService.findAllUsers();
        System.out.println("+------------------------------------+");
        System.out.println("|            Пользователи            |");
        System.out.println("+----ID----|-----------Имя-----------+");
        users.stream().forEach(user -> System.out.println(
                user.getId() + " | " + user.getName()
        ));
    }

    @Bean
    public void writeUserByName(List<User> users){
        if (users == null){
            System.out.println("пользователь с таким именем не найден");
        } else {
            System.out.println("+------------------------------------+");
            System.out.println("|            Пользователи            |");
            System.out.println("+----ID----|-----------Имя-----------+");
            users.stream().forEach(user -> System.out.println(
                    user.getId() + " | " + user.getName()
            ));
        }
    }
}
