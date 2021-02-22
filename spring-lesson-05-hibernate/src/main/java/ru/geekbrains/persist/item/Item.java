package ru.geekbrains.persist.item;

import javax.persistence.*;

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
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
