package com.techtestspringboot.msig.service.impl;

import com.techtestspringboot.msig.dto.ProductDTO;
import com.techtestspringboot.msig.entity.Product;
import com.techtestspringboot.msig.repository.ProductRepository;
import com.techtestspringboot.msig.service.ApprovalService;
import com.techtestspringboot.msig.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final ProductRepository productRepository;
    private final ValidationService validationService;

    @Autowired
    public ApprovalServiceImpl(ProductRepository productRepository, ValidationService validationService) {
        this.productRepository= productRepository;
        this.validationService = validationService;
    }

    @Override
    public List<ProductDTO> getAllPendingStatus() {
        List<Product> products = productRepository.findByStatus("Pending");

        return products.stream()
                .map(this::responseToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO approvedStatusById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        validationService.validateStatusChange(product.getStatus(), "Approved");

        product.setStatus("Approved");
        productRepository.save(product);
        return responseToProductDTO(product);
    }

    @Override
    public ProductDTO rejectedStatusById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        validationService.validateStatusChange(product.getStatus(), "Rejected");

        product.setStatus("Rejected");
        productRepository.save(product);
        return responseToProductDTO(product);
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
