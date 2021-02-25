package ru.geekbrains.service.item;

import ru.geekbrains.persist.item.Item;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


public class ItemDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private BigDecimal price;

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        id = item.getId();
        name = item.getName();
        price = item.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
