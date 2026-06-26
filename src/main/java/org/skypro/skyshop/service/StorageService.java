package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        fillTestData();
    }

    private void fillTestData() {
        Product bread = new SimpleProduct(UUID.randomUUID(), "Хлеб", 50);
        Product milk = new DiscountedProduct(UUID.randomUUID(), "Молоко", 80, 10);
        Product eggs = new DiscountedProduct(UUID.randomUUID(), "Яйца", 120, 5);
        Product cheese = new FixPriceProduct(UUID.randomUUID(), "Сыр");
        Product butter = new SimpleProduct(UUID.randomUUID(), "Масло", 150);
        Product chocolate = new FixPriceProduct(UUID.randomUUID(), "Шоколад");

        productMap.put(bread.getId(), bread);
        productMap.put(milk.getId(), milk);
        productMap.put(eggs.getId(), eggs);
        productMap.put(cheese.getId(), cheese);
        productMap.put(butter.getId(), butter);
        productMap.put(chocolate.getId(), chocolate);

        Article article1 = new Article(UUID.randomUUID(), "Польза хлеба", "Хлеб содержит много углеводов и полезен для энергии.");
        Article article2 = new Article(UUID.randomUUID(), "Как выбрать сыр", "Сыр бывает твёрдый и мягкий. Ищите качественный сыр.");
        Article article3 = new Article(UUID.randomUUID(), "Рецепт яичницы", "Яйца пожарьте на масле, добавьте соль.");

        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }

    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(productMap.values());
        all.addAll(articleMap.values());
        return all;
    }
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

}