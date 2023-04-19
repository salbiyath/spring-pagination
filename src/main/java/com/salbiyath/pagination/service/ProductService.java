package com.salbiyath.pagination.service;

import com.salbiyath.pagination.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(String id);
    Product createProduct(Product product);
    Product updateProduct(String id, Product product);
    void deleteProduct(String id);
    Page<Product> getAllProductsPaged(Pageable pageable);

}
