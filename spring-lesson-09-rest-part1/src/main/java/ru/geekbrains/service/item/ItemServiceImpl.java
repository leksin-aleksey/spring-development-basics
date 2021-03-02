package ru.geekbrains.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.item.Item;
import ru.geekbrains.persist.item.ItemRepository;
import ru.geekbrains.persist.item.ItemSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ItemDTO> findWithFilter(String nameFilter, Integer minPrice, Integer maxPrice,
                                        Integer page, Integer size, String sortField) {
        Specification<Item> spec = Specification.where(null);
        if (nameFilter != null && !nameFilter.isBlank()) {
            spec = spec.and(ItemSpecification.usernameLike(nameFilter));
        }
        if (minPrice != null) {
            spec = spec.and(ItemSpecification.minPrice(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ItemSpecification.maxPrice(maxPrice));
        }
        if (sortField != null && !sortField.isBlank()) {
            return itemRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                    .map(ItemDTO::new);
        }
        Page<Item> temp = itemRepository.findAll(spec, PageRequest.of(page, size));
        return itemRepository.findAll(spec, PageRequest.of(page, size))
                .map(ItemDTO::new);
    }

    @Override
    public Optional<ItemDTO> findById(long id) {
        return itemRepository.findById(id)
                .map(ItemDTO::new);
    }

    @Transactional
    @Override
    public void save(ItemDTO itemDTO) {
        Item item = new Item(itemDTO);
        itemRepository.save(item);
        if (itemDTO.getId() == null){
            itemDTO.setId(item.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        itemRepository.deleteById(id);
    }
}
