package com.salbiyath.pagination.controller;

import com.salbiyath.pagination.model.Product;
import com.salbiyath.pagination.repository.ProductRepository;
import com.salbiyath.pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    private ProductRepository productRepository;

    @Autowired
    void setProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pages")
    public Page<Product> getAllProductsPaged(@RequestParam int page, @RequestParam int size, @RequestParam String sortField, @RequestParam String sortOrder) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProductsPaged(pageable);
    }

    @GetMapping("/{offset}/{pageSize}")
    public Page<Product> getAllProductsPaged(@PathVariable int offset, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        return productService.getAllProductsPaged(pageable);
    }

    @GetMapping("/{offset}/{pageSize}/{sortField}")
    public Page<Product> getAllProductsPagedWithSortField(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String sortField) {
        Pageable pageable = PageRequest.of(offset, pageSize).withSort(Sort.by(sortField).ascending());
        return productService.getAllProductsPaged(pageable);
    }

    @GetMapping("/{offset}/{pageSize}/{sortField}/{sortOrder}")
    public Page<Product> getAllProductsPagedWithSortFieldAndSortOrder(@PathVariable int offset, @PathVariable int pageSize,
                                                          @PathVariable String sortField, @PathVariable String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(offset, pageSize, sort);
        return productService.getAllProductsPaged(pageable);
    }
}
