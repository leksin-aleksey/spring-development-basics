package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "allCustomers", query = "from Customer c"),
        @NamedQuery(name = "customerById", query = "from Customer c where c.id = :id"),
        @NamedQuery(name = "customerByName", query = "from Customer c where c.name = :name")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
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
