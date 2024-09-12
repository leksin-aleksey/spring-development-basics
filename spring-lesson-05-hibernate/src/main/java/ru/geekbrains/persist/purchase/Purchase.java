package ru.geekbrains.persist.purchase;

import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.item.Item;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal purchase;

    public Purchase() {
    }

    public Purchase(Customer customer, Item item, BigDecimal purchase) {
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

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer=" + customer +
                ", item=" + item +
                ", purchase=" + purchase +
                '}';
    }
}
