package com.cout.shop.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReceiptHasProduct {
    private int id;
    private int receiptId;
    private int productId;
    private int count;
    private int price;
}
