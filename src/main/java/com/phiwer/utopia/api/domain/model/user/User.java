package com.phiwer.utopia.api.domain.model.user;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "UT_User", indexes = {
        @Index(name = "idx_user_email", columnList = "email")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_email", columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    public User(final String email, final String firstName, final String lastName, final String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    protected User() {
    }
}
