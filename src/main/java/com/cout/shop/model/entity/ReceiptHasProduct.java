package com.cout.shop.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReceiptHasProduct {
    private int id;
    private Receipt receipt;
    private Product product;
    private int count;
    private int price;
}
