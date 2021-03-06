package ru.geekbrains.persist.cart;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.customer.CustomerRepository;
import ru.geekbrains.persist.item.Item;
import ru.geekbrains.persist.item.ItemRepository;
import ru.geekbrains.service.cart.CartDTO;
import ru.geekbrains.service.customer.CustomerDTO;
import ru.geekbrains.service.item.ItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class CartRepository {
    private final Map<Long, Cart> carts;

    public CartRepository(CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.carts = new ConcurrentHashMap<>();

        // INIT (NO DB)
        List<Item> itemsIds = new ArrayList<>();
        Cart cart = new Cart(customerRepository.getOne(1L));
        cart.setItems(itemsIds);
        carts.put(1L, cart);

        cart = new Cart(customerRepository.getOne(2L));
        itemsIds = new ArrayList<>();
        itemsIds.add(itemRepository.findById(1L).get());
        itemsIds.add(itemRepository.findById(2L).get());
        cart.setItems(itemsIds);
        carts.put(2L, cart);

        cart = new Cart(customerRepository.getOne(3L));
        itemsIds = new ArrayList<>();
        itemsIds.add(itemRepository.findById(3L).get());
        itemsIds.add(itemRepository.findById(4L).get());
        itemsIds.add(itemRepository.findById(5L).get());
        cart.setItems(itemsIds);
        carts.put(2L, cart);
    }

    public void add(Cart cart){
        carts.put(cart.getId(), cart);
    }

    public void delete(long id){
        carts.remove(id);
    }

    public Cart getById(long id){
        return carts.get(id);
    }

    public List<Cart> getAll(){
        return new ArrayList<>(carts.values());
    }

    public Set<Long> getAllIds(){
        return carts.keySet();
    }
}
