package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query(nativeQuery = true ,value = "INSERT INTO USER (username, password, birth_date) VALUES ('User4', 'password4', '2004-04-04 04:04:04');")
    int nativeInsert();
}
