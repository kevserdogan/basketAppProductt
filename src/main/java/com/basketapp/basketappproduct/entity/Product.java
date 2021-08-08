package com.basketapp.basketappproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String serialNumber;
    private String name;
    private String type; //TODO: enum yapılacak.
    private double price;
    private  int totalProduct;
}
