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
    private ReceiptStatus status;
    private User user;
    private Timestamp createDate;
    private Timestamp updateDate;
}
