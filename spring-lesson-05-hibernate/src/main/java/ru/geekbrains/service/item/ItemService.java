package ru.geekbrains.service.item;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<ItemDTO> findAll();

    Page<ItemDTO> findWithFilter(String nameFilter, Integer minPrice, Integer maxPrice,
                                 Integer page, Integer size, String sortField);

    Optional<ItemDTO> findById(long id);

    void save(ItemDTO itemDTO);

    void delete(long id);
}
