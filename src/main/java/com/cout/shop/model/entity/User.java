package com.cout.shop.model.entity;

import lombok.*;

import javax.management.relation.Role;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    private Timestamp createTime;
    private UserRole role;
    private UserStatus userStatus;

    public User(String login, String email, String password, UserRole role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(String login, String email, String password, UserRole role, UserStatus userStatus) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }
}
