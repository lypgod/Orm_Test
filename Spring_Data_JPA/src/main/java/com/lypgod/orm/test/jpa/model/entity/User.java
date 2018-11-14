package com.lypgod.orm.test.jpa.model.entity;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Cacheable
@SQLDelete(sql = "update user set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_users",
                query = "SELECT u FROM User u"),
        @NamedQuery(name = "query_get_all_users_join_fetch_orders",
                query = "SELECT DISTINCT u FROM User u JOIN FETCH u.orders o")})
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

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new HashSet<>();


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

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
