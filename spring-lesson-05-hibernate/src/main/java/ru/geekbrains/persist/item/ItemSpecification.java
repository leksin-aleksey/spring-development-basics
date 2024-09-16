package ru.geekbrains.persist.item;

import org.springframework.data.jpa.domain.Specification;


public final class ItemSpecification {

    public static Specification<Item> usernameLike(String name) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Item> minPrice(Integer minPrice) {
        return (root, query, cb) -> cb.ge(root.get("price"), minPrice);
    }

    public static Specification<Item> maxPrice(Integer maxPrice) {
        return (root, query, cb) -> cb.le(root.get("price"), maxPrice);
    }
}
