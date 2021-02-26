package ru.geekbrains.service.customer;

import ru.geekbrains.persist.customer.Customer;

import javax.validation.constraints.NotEmpty;


public class CustomerDTO {

    private Long id;

    @NotEmpty
    private String name;

    public CustomerDTO() {
    }

    public CustomerDTO(String name) {
        this.name = name;
    }

    public CustomerDTO(Customer customer){
        name = customer.getName();
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
