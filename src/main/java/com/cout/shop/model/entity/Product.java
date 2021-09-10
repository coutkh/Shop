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

    public Product(int id, String name, int count, int price, String color) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.color = color;
    }
}
