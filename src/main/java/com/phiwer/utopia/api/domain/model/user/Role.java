package com.phiwer.utopia.api.domain.model.user;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ut_role", uniqueConstraints = {
        @UniqueConstraint(name = "uc_role_name", columnNames = {"name"})
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 20, nullable = false)
    private String name;

    public Role(final String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    protected Role() {}
}
