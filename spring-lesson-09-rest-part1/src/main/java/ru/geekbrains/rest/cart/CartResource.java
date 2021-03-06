package ru.geekbrains.rest.cart;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.item.BadRequestException;
import ru.geekbrains.controller.item.NotFoundException;
import ru.geekbrains.service.cart.CartDTO;
import ru.geekbrains.service.cart.CartService;

import java.util.List;


@Tag(name = "Cart API", description = "Cart manipulation API")
@RestController
@RequestMapping("/api/v1/cart")
public class CartResource {
    private CartService cartService;

    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "all")
    public List<CartDTO> findAll(){
        return cartService.getAll();
    }

    @GetMapping(path = "/{id}")
    public CartDTO findById(@PathVariable("id") long id){
        if (!cartService.getAllIds().contains(id)){
            throw new NotFoundException();
        }
        return cartService.getById(id);
    }

    @PostMapping
    public CartDTO create(@RequestBody CartDTO cartDTO){
        if (cartDTO.getId() != null){
            throw new BadRequestException();
        }
        cartService.add(cartDTO);
        return cartDTO;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id){
        if (!cartService.getAllIds().contains(id)){
            throw new NotFoundException();
        }
        cartService.delete(id);
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
