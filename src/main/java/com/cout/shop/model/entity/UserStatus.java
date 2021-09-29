package com.cout.shop.model.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum UserStatus {
    UNLOCKED{{
        this.id = 1;
        this.userStatus = "unlocked";
    }},
    LOCKED{{
        this.id = 2;
        this.userStatus = "locked";
    }};

    int id;
    String userStatus;

    public int getId() {
        return id;
    }

    public String getUserStatus() {
        return userStatus;
    }
}
