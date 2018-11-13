package com.lypgod.orm.test.jpa.model.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update user set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime birthDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Passport passport;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

//    @ManyToMany(mappedBy="users")
//    private List<Role> roles = new ArrayList<>();


    @PreRemove
    private void preRemove() {
        this.isDeleted = true;
    }

    public void setPassport(Passport passport) {
        passport.setUser(this);
        this.passport = passport;
    }

    public void removePassport(Passport passport) {
        if (passport != null) {
            passport.setUser(null);
            this.passport = null;
        }
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.setUser(null);
    }
}
