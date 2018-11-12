package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.Order;
import com.lypgod.orm.test.jpa.model.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Before
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

        Order user1_order = new Order();
        user1_order.setName("user1_order");
        user1_order.setQuantity(11);
        user1_order.setUser(user1);
        this.orderRepository.save(user1_order);

        Order user2_order = new Order();
        user2_order.setName("user2_order");
        user2_order.setUser(user2);
        this.orderRepository.save(user2_order);


        this.entityManager.clear();
        log.warn("----------------------------------- data init -----------------------------------------");
    }

    @Test
    @Transactional
    public void insertUserTest() {
        User newUser = new User();
        newUser.setUsername("user4");
        newUser.setPassword("password4");
        newUser.setBirthDate(LocalDateTime.of(2004, 4, 4, 4, 4, 4));
        assertEquals(this.userRepository.save(newUser).getId().intValue(), 4);
        assertEquals(this.userRepository.findAll().size(), 4);
    }

    @Test
    @Transactional
    public void deletetUserTest() throws Exception {
        User user = this.userRepository.findById(3).orElseThrow(Exception::new);
        this.userRepository.delete(user);
        assertEquals(this.userRepository.findAll().size(), 2);
    }

    @Test
    @Transactional
    public void updateUserTest() throws Exception {
        User user = this.userRepository.findById(3).orElseThrow(Exception::new);
        user.setUsername("user3");
        user.setPassword("password33");
        this.userRepository.save(user);
        this.userRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void printAllUsersTest() {
//        this.userRepository.findAll().forEach(log::warn);
        this.orderRepository.findAll().stream().map(Order::getUser).collect(Collectors.toList()).forEach(log::warn);
    }

}