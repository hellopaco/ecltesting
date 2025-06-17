package ecl.testing.eclbackend.store;

import ecl.testing.eclbackend.model.Order;
import ecl.testing.eclbackend.model.Product;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory data store.
 */

@Component
public class DataStore {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong orderIdCounter = new AtomicLong(1);

    @PostConstruct
    public void init() {
        products.put(1L, new Product(1L, "Laptop", 1200.0, 10));
        products.put(2L, new Product(2L, "Phone", 800.0, 15));
        products.put(3L, new Product(3L, "Headphones", 100.0, 30));
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public Map<Long, Order> getOrders() {
        return orders;
    }

    public AtomicLong getOrderIdCounter() {
        return orderIdCounter;
    }
}
