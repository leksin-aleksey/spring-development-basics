package ru.geekbrains.service.cart;

import ru.geekbrains.persist.cart.Cart;
import ru.geekbrains.persist.customer.Customer;
import ru.geekbrains.persist.item.Item;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class CartDTO {
    private Long id;

    @NotEmpty
    private long customerId;

    private List<Long> itemIds;

    public CartDTO() {
    }

    public CartDTO(Customer customer){
        this.customerId = customer.getId();
    }

    public CartDTO(Cart cart){
        id = cart.getId();
        customerId = cart.getCustomer().getId();
        itemIds = cart.getItems().stream()
                .map(Item::getId)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", itemIds=" + itemIds +
                '}';
    }
}
