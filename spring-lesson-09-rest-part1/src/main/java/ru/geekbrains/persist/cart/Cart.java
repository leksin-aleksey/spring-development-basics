package ru.geekbrains.persist.cart;

import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.item.Item;
import ru.geekbrains.service.cart.CartDTO;

import java.util.List;

public class Cart {
    private static long counter = 1;

    private Long id;

    private Customer customer;

    private List<Item> items;

    public Cart() {
        id = counter++;
    }

    public Cart(Customer customer) {
        this();
        this.customer = customer;
    }

    public Cart(CartDTO cartDTO){
        id = cartDTO.getId();
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }
}
