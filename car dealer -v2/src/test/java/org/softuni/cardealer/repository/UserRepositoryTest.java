package org.softuni.cardealer.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private TestEntityManager testEntityManager;

    @Test
    public void findByUsername_returnsUser() {
        userRepository.saveAndFlush(new User() {{
            setUsername("Chris");
            setPassword("password");
            setEmail("chris@gmail.com");
        }});

        userRepository.findByUsername("Chris")
                .ifPresent(user -> {
                    assertEquals(user.getUsername(), "Chris");
                    assertEquals(user.getPassword(), "Password");
                    assertEquals(user.getEmail(), "chris@gmail.com");
                });

//        user = testEntityManager.persistAndFlush(user);
//
//        final User expected = testEntityManager.find(User.class, user.getId());
//
//        assertEquals(expected.getUsername(), user.getUsername());

//        userRepository.findByUsername("Chris")
//                .ifPresent(foundUser -> {
//                    assertThat(foundUser.getUsername(), is("Chris"));
//                    assertThat(foundUser.getEmail(), is("chris@gmail.com"));
//                    assertEquals(foundUser.getPassword(), "password");
//                });

    }
}