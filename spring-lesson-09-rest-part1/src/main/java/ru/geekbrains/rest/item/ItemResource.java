package ru.geekbrains.rest.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.item.BadRequestException;
import ru.geekbrains.controller.item.NotFoundException;
import ru.geekbrains.service.item.ItemDTO;
import ru.geekbrains.service.item.ItemService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/item")
public class ItemResource {
    private ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/all")
    public List<ItemDTO> findAll(){
        return new ArrayList<>(itemService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ItemDTO findById(@PathVariable("id") long id){
        return itemService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO itemDTO){
        if (itemDTO.getId() != null){
            throw new BadRequestException();
        }
        itemService.save(itemDTO);
        return itemDTO;
    }

    @PutMapping
    public void update(@RequestBody ItemDTO itemDTO){
        if (itemDTO.getId() == null){
            throw new BadRequestException();
        }
        itemService.save(itemDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        itemService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException e){
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException e){
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }
}