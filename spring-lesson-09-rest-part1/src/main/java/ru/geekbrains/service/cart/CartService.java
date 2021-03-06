package ru.geekbrains.service.cart;

import ru.geekbrains.persist.cart.Cart;

import java.util.List;
import java.util.Set;

public interface CartService {

    void add(CartDTO cartDTO);

    void delete(long id);

    CartDTO getById(long id);

    List<CartDTO> getAll();

    Set<Long> getAllIds();
}
