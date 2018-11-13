package com.lypgod.orm.test.jpa.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@ToString(exclude = "user")
@Entity
@DynamicInsert
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne()
    private User user;
}
