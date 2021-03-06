package ru.geekbrains.service.cart;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.item.NotFoundException;
import ru.geekbrains.persist.cart.Cart;
import ru.geekbrains.persist.cart.CartRepository;
import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.customer.CustomerRepository;
import ru.geekbrains.persist.item.Item;
import ru.geekbrains.persist.item.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{
    private CartRepository cartRepository;

    private CustomerRepository customerRepository;

    private ItemRepository itemRepository;

    public CartServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void add(CartDTO cartDTO) {
        Cart cart = new Cart();
        long customerId = cartDTO.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
        cart.setCustomer(customer);
        List<Item> items = new ArrayList<>();
        for (long id : cartDTO.getItemIds()) {
            items.add(itemRepository.findById(id).orElseThrow(NotFoundException::new));
        }
        cart.setItems(items);
        cartRepository.add(cart);
    }

    @Override
    public void delete(long id) {
        cartRepository.delete(id);
    }

    @Override
    public CartDTO getById(long id) {
        Cart cart = cartRepository.getById(id);
        if (cart == null) {
            return null;
        }
        return new CartDTO(cart);
    }

    @Override
    public List<CartDTO> getAll() {
        return cartRepository.getAll()
                .stream().map(CartDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Long> getAllIds() {
        return cartRepository.getAllIds();
    }
}
