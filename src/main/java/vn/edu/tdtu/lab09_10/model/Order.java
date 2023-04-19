package vn.edu.tdtu.lab09_10.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter @Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_cart;
    private String username;
    private String phone;
    private String address;
    private Date date_create;
    private double total;
    private String status;

    public Order () {}

    public Order(int id_cart, String username, String phone, String address, Date date_create, double total, String status) {
        this.id_cart = id_cart;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.date_create = date_create;
        this.total = total;
        this.status = status;
    }

    public Order(int id, int id_cart, String username, String phone, String address, Date date_create, double total, String status) {
        this.id = id;
        this.id_cart = id_cart;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.date_create = date_create;
        this.total = total;
        this.status = status;
    }
}