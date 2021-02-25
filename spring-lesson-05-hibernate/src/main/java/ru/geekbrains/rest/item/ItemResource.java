package ru.geekbrains.rest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.item.BadRequestException;
import ru.geekbrains.controller.item.NotFoundException;
import ru.geekbrains.service.item.ItemDTO;
import ru.geekbrains.service.item.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/item")
public class ItemResource {

    private final ItemService itemService;

    @Autowired
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

//    @GetMapping("filter")
//    public Page<ItemDTO> listPage(
//                           @RequestParam("nameFilter") Optional<String> usernameFilter,
//                           @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
//                           @RequestParam("ageMaxFilter") Optional<Integer> ageMaxFilter,
//                           @RequestParam("page") Optional<Integer> page,
//                           @RequestParam("size") Optional<Integer> size,
//                           @RequestParam("sortField") Optional<String> sortField) {
//
//        return itemService.findWithFilter(
//                nameFilter.orElse(null),
//                ageMinFilter.orElse(null),
//                ageMaxFilter.orElse(null),
//                page.orElse(1) - 1,
//                size.orElse(3),
//                sortField.orElse(null)
//        );
//    }

    @PostMapping(consumes = "application/json")
    public ItemDTO create(@RequestBody ItemDTO itemDTO) {
        if (itemDTO.getId() != null) {
            throw new BadRequestException();
        }
        itemService.save(itemDTO);
        return itemDTO;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ItemDTO itemDTO) {
        if (itemDTO.getId() == null) {
            throw new BadRequestException();
        }
        itemService.save(itemDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }
}
