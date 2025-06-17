package ecl.testing.eclbackend.controller;

import ecl.testing.eclbackend.model.Order;
import ecl.testing.eclbackend.model.OrderRequest;
import ecl.testing.eclbackend.model.Product;
import ecl.testing.eclbackend.store.DataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final DataStore store;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        Product product = store.getProducts().get(request.getProductId());

        if (product == null || product.getStock() < request.getQuantity()) {
            return ResponseEntity.badRequest().body("Product not found or insufficient stock.");
        }

        synchronized (product) {
            if (product.getStock() < request.getQuantity()) {
                return ResponseEntity.badRequest().body("Insufficient stock.");
            }
            product.setStock(product.getStock() - request.getQuantity());
        }

        long orderId = store.getOrderIdCounter().getAndIncrement();
        double total = product.getPrice() * request.getQuantity();
        Order order = new Order(orderId, product.getId(), request.getQuantity(), total);
        store.getOrders().put(orderId, order);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = store.getOrders().get(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }
}
