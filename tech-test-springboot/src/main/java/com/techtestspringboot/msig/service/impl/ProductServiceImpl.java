package com.techtestspringboot.msig.service.impl;

import com.techtestspringboot.msig.dto.ProductDTO;
import com.techtestspringboot.msig.entity.Product;
import com.techtestspringboot.msig.repository.ProductRepository;
import com.techtestspringboot.msig.service.ProductService;
import com.techtestspringboot.msig.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ValidationService validationService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository = productRepository;
        this.validationService = validationService;
    }

    @Override
    public ProductDTO addProduct(ProductDTO request) {

        validationService.validate(request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStatus("Pending");
        productRepository.save(product);

        return responseToProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO request, Long id) {

        validationService.validate(request);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        productRepository.save(product);

        return responseToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::responseToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(product);
    }

    public ProductDTO responseToProductDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .status(product.getStatus())
                .build();
    }
}
