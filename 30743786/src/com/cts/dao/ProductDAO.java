package com.cts.dao;

import com.cts.model.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
