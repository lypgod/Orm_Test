package com.lypgod.orm.test.jpa.model.repository;

import com.lypgod.orm.test.jpa.model.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {

}
