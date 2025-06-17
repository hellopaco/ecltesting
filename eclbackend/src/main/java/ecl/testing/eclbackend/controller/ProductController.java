package ecl.testing.eclbackend.controller;

import ecl.testing.eclbackend.store.DataStore;
import ecl.testing.eclbackend.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final DataStore store;

    @GetMapping
    public List<Product> getProducts() {
        return store.getProducts().values().stream().toList();
    }
}