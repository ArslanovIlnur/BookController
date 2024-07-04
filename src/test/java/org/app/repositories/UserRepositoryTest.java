package org.app.repositories;

import jakarta.persistence.EntityManager;
import org.app.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserByName() {
        User user = new User();
        user.setName("John");

        entityManager.persist(user);
        entityManager.flush();

        List<User> users = userRepository.getUserByName("John");
        assertEquals(user.getName(), users.get(0).getName());
    }
}