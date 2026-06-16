package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Товар с ID " + id + " не найден"));
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = productBasket.getProducts();
        List<BasketItem> items = new ArrayList<>();
        for (Map.Entry<UUID, Integer> entry : basketMap.entrySet()) {
            UUID id = entry.getKey();
            int quantity = entry.getValue();
            Product product = storageService.getProductById(id).orElseThrow();
            items.add(new BasketItem(product, quantity));
        }
        return new UserBasket(items);
    }
}