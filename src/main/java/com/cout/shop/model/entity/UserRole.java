package com.cout.shop.model.entity;

import lombok.NoArgsConstructor;


@NoArgsConstructor

public enum UserRole {
    GUEST{{
            this.id = 1;
            this.role = "guest";
        }},
    USER{{
        this.id = 2;
        this.role = "user";
    }},
    ADMIN{{
        this.id = 3;
        this.role = "user";
    }};

    int id;
    String role;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
