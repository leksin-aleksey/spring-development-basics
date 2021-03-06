package ru.geekbrains.persist.cart;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.customer.CustomerRepository;
import ru.geekbrains.persist.item.Item;
import ru.geekbrains.persist.item.ItemRepository;
import ru.geekbrains.service.cart.CartDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private static long counter = 1;

//    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
    private ItemRepository itemRepository;

    private Long id;

    private Customer customer;

    private List<Item> items;

    public Cart() {
    }

    public Cart(Customer customer) {
        id = counter++;
        this.customer = customer;
    }

    public Cart(CartDTO cartDTO){
        id = cartDTO.getId();
//        customer = customerRepository.findById(cartDTO.getCustomerId()).get();
//        for (long id : cartDTO.getItemIds()) {
//            items.add(itemRepository.getOne(id));
//        }
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
