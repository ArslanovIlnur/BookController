package org.app.service;

import org.app.entities.Book;
import org.app.entities.User;
import org.app.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findAllUsers() {
        UserService userService = new UserService(userRepository);

        assertEquals(0, userService.findAllUsers().size());

        User user = new User();
        user.setName("user");
        entityManager.persist(user);
        entityManager.flush();

        assertNotEquals(0, userService.findAllUsers().size());
    }

    @Test
    void getUserByName() {
        UserService userService = new UserService(userRepository);

        assertEquals(0, userService.findAllUsers().size());

        User user = new User();
        user.setName("user");
        entityManager.persist(user);
        entityManager.flush();

        String name = "user";
        InputStream inputStream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8));

        System.setIn(inputStream);

        List<User> users = userService.getUserByName();
        assertNotNull(users);
    }

    @Test
    void deleteUser() {
        UserService userService = new UserService(userRepository);

        assertEquals(0, userService.findAllUsers().size());

        User user = new User();
        user.setName("user");
        entityManager.persist(user);
        entityManager.flush();

        assertNotEquals(0, userRepository.getUserByName("test"));

        Long id = user.getId();
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(id).getBytes(StandardCharsets.UTF_8));

        System.setIn(inputStream);
        userService.deleteUser();
        assertEquals(0, userRepository.getUserByName("test").size());
    }
}