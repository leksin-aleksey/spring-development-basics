package ru.geekbrains.controller.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.service.item.ItemDTO;
import ru.geekbrains.service.item.ItemService;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("priceMinFilter") Optional<Integer> priceMinFilter,
                           @RequestParam("priceMaxFilter") Optional<Integer> priceMaxFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField) {
        Page<ItemDTO> items = itemService.findWithFilter(nameFilter.orElse(null),
                priceMinFilter.orElse(null), priceMaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(3),
                sortField.orElse(null));
        model.addAttribute("items", items);
        return "item";
    }

//    @GetMapping
//    public String listPage(Model model,
//                           @RequestParam("usernameFilter") Optional<String> usernameFilter,
//                           @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
//                           @RequestParam("ageMaxFilter") Optional<Integer> ageMaxFilter,
//                           @RequestParam("page") Optional<Integer> page,
//                           @RequestParam("size") Optional<Integer> size,
//                           @RequestParam("sortField") Optional<String> sortField) {
//        logger.info("List page requested");
//
//        Page<ItemDTO> users = itemService.findWithFilter(
//                usernameFilter.orElse(null),
//                ageMinFilter.orElse(null),
//                ageMaxFilter.orElse(null),
//                page.orElse(1) - 1,
//                size.orElse(3),
//                sortField.orElse(null)
//        );
//        model.addAttribute("users", users);
//        return "item";
//    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("item", itemService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "item_form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") ItemDTO itemDTO, BindingResult result, Model model) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "item_form";
        }

        logger.info("Updating item with id {}", itemDTO.getId());
        itemService.save(itemDTO);
        return "redirect:/item";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new item request");

        model.addAttribute("item", new ItemDTO());
        return "item_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Item delete request");

        itemService.delete(id);
        return "redirect:/item";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}
