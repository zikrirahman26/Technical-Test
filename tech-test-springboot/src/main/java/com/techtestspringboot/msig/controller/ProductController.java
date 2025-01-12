package com.techtestspringboot.msig.controller;

import com.techtestspringboot.msig.dto.ProductDTO;
import com.techtestspringboot.msig.dto.WebResponse;
import com.techtestspringboot.msig.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductDTO> addProduct(@RequestBody ProductDTO request) {
        ProductDTO response = productService.addProduct(request);
        return WebResponse.<ProductDTO>builder()
                .data(response)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductDTO> updateProduct(@RequestBody ProductDTO request, @PathVariable Long id) {
        ProductDTO response = productService.updateProduct(request, id);
        return WebResponse.<ProductDTO>builder()
                .data(response).responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> response = productService.getAllProducts();
        return WebResponse.<List<ProductDTO>>builder()
                .data(response)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return WebResponse.<String>builder()
                .data(null)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
