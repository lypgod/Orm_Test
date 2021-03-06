package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.Role;
import com.lypgod.orm.test.jpa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByRolesEquals(Role role);
}
