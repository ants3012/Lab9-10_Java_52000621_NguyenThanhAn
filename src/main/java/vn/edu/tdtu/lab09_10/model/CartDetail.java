package vn.edu.tdtu.lab09_10.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "cart_detail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_cart;
    private int id_product;
    private String name_product;
    private int quantity;
    private double price;
    private double sum_price;

    public CartDetail() {}
    public CartDetail(int id_cart, int id_product, String name_product, int quantity, double price, double sum_price) {
        this.id_cart = id_cart;
        this.id_product = id_product;
        this.name_product = name_product;
        this.quantity = quantity;
        this.price = price;
        this.sum_price = sum_price;
    }

    public CartDetail(int id, int id_cart, int id_product, String name_product, int quantity, double price, double sum_price) {
        this.id = id;
        this.id_cart = id_cart;
        this.id_product = id_product;
        this.name_product = name_product;
        this.quantity = quantity;
        this.price = price;
        this.sum_price = sum_price;
    }
}