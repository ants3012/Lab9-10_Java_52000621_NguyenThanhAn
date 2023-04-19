package vn.edu.tdtu.lab09_10.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_user;
    private Date dateCreate;
    private boolean checkout;
    private double total;
    public Cart() {}

    public Cart(int id_user, Date dateCreate, boolean checkout, double total) {
        this.id_user = id_user;
        this.dateCreate = dateCreate;
        this.checkout = checkout;
        this.total = total;
    }

    public Cart(int id, int id_user, Date dateCreate, boolean checkout, double total) {
        this.id = id;
        this.id_user = id_user;
        this.dateCreate = dateCreate;
        this.checkout = checkout;
        this.total = total;
    }
}