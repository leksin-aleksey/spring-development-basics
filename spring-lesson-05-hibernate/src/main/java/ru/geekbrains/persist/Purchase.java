package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private int purchase;

    public Purchase() {
    }

    public Purchase(Customer customer, Item item, int purchase) {
        this.customer = customer;
        this.item = item;
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }
}
