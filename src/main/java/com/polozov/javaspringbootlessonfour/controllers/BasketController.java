package com.polozov.javaspringbootlessonfour.controllers;


import com.polozov.javaspringbootlessonfour.entities.Product;
import com.polozov.javaspringbootlessonfour.services.ProductService;
import com.polozov.javaspringbootlessonfour.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private List<Product> basketList = new ArrayList<>();
    private Map<String, List<Product>> basketHashMap = new HashMap<>();
    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showBasket(Model model, Principal principal) {
        basketList = basketHashMap.get(principal.getName());
        model.addAttribute("basketList", basketList);
        return "product_views/basket";
    }

    @GetMapping("/{id}")
    public String addProductToBasket(@PathVariable(value = "id") Long id, Principal principal) {
        if (basketHashMap.get(principal.getName()) == null) {
            basketHashMap.put(principal.getName(), new ArrayList<>());
        }
        basketHashMap.get(principal.getName()).add(productService.getById(id).get());
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id, Principal principal) {
        basketList = basketHashMap.get(principal.getName());
        IntStream.range(0, basketList.size()).filter(i -> basketList.get(i).getId() == id).findFirst().ifPresent(basketList::remove);
        return "redirect:/basket";
    }

}
