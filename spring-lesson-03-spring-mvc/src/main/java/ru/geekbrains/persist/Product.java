package ru.geekbrains.persist;

import javax.validation.constraints.NotEmpty;

public class Product {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private int price;

    public Product() {
    }

    public Product(@NotEmpty String name) {
        this.name = name;
    }

    public Product(@NotEmpty String name, @NotEmpty String description, @NotEmpty int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}