package com.techtestspringboot.msig.service;

import com.techtestspringboot.msig.dto.ProductDTO;

import java.util.List;

public interface ApprovalService {

    List<ProductDTO> getAllPendingStatus();

    ProductDTO approvedStatusById(Long id);

    ProductDTO rejectedStatusById(Long id);
}
