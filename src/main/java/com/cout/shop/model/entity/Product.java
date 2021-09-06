package com.cout.shop.model.entity;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    private int id;
    private String name;
    private int count;
    private int price;
    private String color;
    private Category category;
    private Timestamp createDate;
    private Timestamp lastUpdate;

}
