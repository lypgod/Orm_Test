package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

//    @Before
    public void setUp() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setBirthDate(LocalDateTime.of(1111, 1, 1, 1, 1, 1));
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setBirthDate(LocalDateTime.of(2222, 2, 2, 2, 2, 2));
        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword("password3");
        user3.setBirthDate(LocalDateTime.of(3333, 3, 3, 3, 3, 3));
        this.userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }

    @Test
    @Transactional
    public void insertUserTest() {
//        User newUser = new User();
//        newUser.setUsername("user4");
//        newUser.setPassword("password4");
//        newUser.setBirthDate(LocalDateTime.of(2004, 4, 4, 4, 4, 4));
//        assertEquals(this.userRepository.save(newUser).getId().intValue(), 4);
//        assertEquals(this.userRepository.findAll().size(), 4);

        int i = this.userRepository.nativeInsert();
        System.out.println(i);
    }

}