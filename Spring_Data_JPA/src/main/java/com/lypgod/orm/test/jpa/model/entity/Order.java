package com.lypgod.orm.test.jpa.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@ToString(exclude = "user")
@Entity
@DynamicInsert
@Table(name = "[ORDER]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity;

    @ManyToOne
    private User user;
}
