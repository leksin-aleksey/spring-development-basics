package ru.geekbrains.persist.item;

import ru.geekbrains.service.item.ItemDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@NamedQueries({
        @NamedQuery(name = "allItems", query = "from Item i"),
        @NamedQuery(name = "itemById", query = "from Item i where i.id = :id"),
        @NamedQuery(name = "itemByName", query = "from Item i where i.name = :name")
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    public Item() {
    }

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Item(ItemDTO itemDTO){
        name = itemDTO.getName();
        price = itemDTO.getPrice();
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
