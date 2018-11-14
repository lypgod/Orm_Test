package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.*;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private PassportRepository passportRepository;
    @Resource
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void oneToOneCascadeTest() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setBirthDate(LocalDateTime.of(1111, 1, 1, 1, 1, 1));

        log.warn("----- add --------------------------------------------------------------------------------------");
        Passport passport1 = new Passport();
        passport1.setName("user1_passport1");

        user1.setPassport(passport1);
        this.userRepository.save(user1);

        log.warn("----- update -----------------------------------------------------------------------------------");
        Passport passport2 = new Passport();
        passport2.setName("user1_passport2");

        user1.setPassport(passport2);
        this.userRepository.save(user1);

        log.warn("----- delete passport --------------------------------------------------------------------------");
        user1.removePassport(passport2);
        this.userRepository.save(user1);

        log.warn("----- select -----------------------------------------------------------------------------------");
        user1.setPassport(passport1);
        this.userRepository.save(user1);
        this.entityManager.clear();

        Passport passport = this.passportRepository.findById(3).orElseThrow(Exception::new);
        log.warn(passport);
        log.warn(passport.getUser());

        user1 = this.userRepository.findById(1).orElseThrow(Exception::new);
        log.warn(user1);

        log.warn("----- delete user ------------------------------------------------------------------------------");
        this.userRepository.delete(user1);
    }

    @Test
    public void oneToManyAndManyToOneCascadeTest() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setBirthDate(LocalDateTime.of(1111, 1, 1, 1, 1, 1));

        log.warn("----- add --------------------------------------------------------------------------------------");
        Order user1_order1 = new Order();
        user1_order1.setName("user1_order1");
        user1_order1.setQuantity(11);
        user1.addOrder(user1_order1);

        Order user1_order2 = new Order();
        user1_order2.setName("user1_order2");
        user1.addOrder(user1_order2);

        this.userRepository.save(user1);

        log.warn("----- select -----------------------------------------------------------------------------------");
        Order order = this.orderRepository.findById(1).orElseThrow(Exception::new);
        log.warn(order);
        log.warn(order.getUser());
        log.warn(this.userRepository.findById(1).orElseThrow(Exception::new));

        log.warn("----- delete order -----------------------------------------------------------------------------");
        user1.removeOrder(user1_order2);
        this.userRepository.save(user1);

        log.warn("----- delete user ------------------------------------------------------------------------------");
        user1 = this.userRepository.findById(1).orElseThrow(Exception::new);
        this.userRepository.delete(user1);

    }

    @Test
    public void manyToManyCascadeTest() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setBirthDate(LocalDateTime.of(1111, 1, 1, 1, 1, 1));

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setBirthDate(LocalDateTime.of(2222, 2, 2, 2, 2, 2));

        Role roleUser = new Role(RoleName.USER);
        roleUser.setDescription("Role User");
        Role roleAdmin = new Role(RoleName.ADMIN);

        log.warn("----- add --------------------------------------------------------------------------------------");
        user1.addRole(roleUser);
        user1.addRole(roleAdmin);
        user2.addRole(roleUser);
        this.userRepository.saveAll(Arrays.asList(user1, user2));

        log.warn("----- select users with roles ------------------------------------------------------------------");
        this.userRepository.findAll().forEach(log::warn);

        log.warn("----- select roles with users ------------------------------------------------------------------");
        List<Role> roleList = this.roleRepository.findAll();
        Map<Role, List<User>> roleUsersMap = new HashMap<>();
        roleList.forEach(e -> roleUsersMap.put(e, this.userRepository.findAllByRolesEquals(e)));
        log.warn(roleUsersMap);

        log.warn("----- delete role -----------------------------------------------------------------------------");
        user1 = this.userRepository.findById(1).orElseThrow(Exception::new);
        user1.removeRole(roleUser);
        this.userRepository.save(user1);

        log.warn("----- delete user ------------------------------------------------------------------------------");
        user1 = this.userRepository.findById(1).orElseThrow(Exception::new);
        this.userRepository.delete(user1);
    }

    @Test
    public void nPlusOneProblemTest() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setBirthDate(LocalDateTime.of(1111, 1, 1, 1, 1, 1));
        Order user1_order1 = new Order();
        user1_order1.setName("user1_order1");
        user1.addOrder(user1_order1);
        Order user1_order2 = new Order();
        user1_order2.setName("user1_order2");
        user1.addOrder(user1_order2);
        Order user1_order3 = new Order();
        user1_order3.setName("user1_order3");
        user1.addOrder(user1_order3);
        this.userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setBirthDate(LocalDateTime.of(2222, 2, 2, 2, 2, 2));
        Order user2_order1 = new Order();
        user2_order1.setName("user2_order1");
        user2.addOrder(user2_order1);
        Order user2_order2 = new Order();
        user2_order2.setName("user2_order2");
        user2.addOrder(user2_order2);
        Order user2_order3 = new Order();
        user2_order3.setName("user2_order3");
        user2.addOrder(user2_order3);
        this.userRepository.save(user2);

        log.warn("----- select -----------------------------------------------------------------------------------");
        this.userRepository.findAll().forEach(log::warn);

//        EntityGraph<User> userEntityGraph = this.entityManager.createEntityGraph(User.class);
//        userEntityGraph.addSubgraph("orders");

//        EntityGraph<User> userEntityGraph = this.entityManager.createEntityGraph(User.class);
//        userEntityGraph.addSubgraph("orders");
//        List<User> users = this.entityManager
//                .createNamedQuery("query_get_all_users", User.class)
//                .setHint("javax.persistence.loadgraph", userEntityGraph)
//                .getResultList();
//        users.forEach(u -> log.warn("User: {} -> Order: {}", u.getUsername(), u.getOrders()));
    }
}