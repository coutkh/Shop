package com.cout.shop.model.entity;

import lombok.*;

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

}
