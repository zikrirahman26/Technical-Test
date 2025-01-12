package com.techtestspringboot.msig.service;

import com.techtestspringboot.msig.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO request);

    ProductDTO updateProduct(ProductDTO request, Long id);

    List<ProductDTO> getAllProducts();

    void deleteProduct(Long id);
}
