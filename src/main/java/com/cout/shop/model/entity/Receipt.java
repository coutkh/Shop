package com.cout.shop.model.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Receipt {
    private  int id;
    private double total;
    private RecriptStatus status;
    private User user;
    private Timestamp create_date;
    private Timestamp update_date;
}
