package com.techtestspringboot.msig.controller;

import com.techtestspringboot.msig.dto.ProductDTO;
import com.techtestspringboot.msig.dto.WebResponse;
import com.techtestspringboot.msig.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ApprovalController {

    private final ApprovalService approvalService;

    @Autowired
    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping("/pending")
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<List<ProductDTO>> getAllPendingStatus() {
        List<ProductDTO> pendingApproval = approvalService.getAllPendingStatus();
        return WebResponse.<List<ProductDTO>>builder()
                .data(pendingApproval)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PutMapping("/{id}/approve")
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductDTO> approvedStatusById(@PathVariable Long id) {
        ProductDTO response = approvalService.approvedStatusById(id);
        return WebResponse.<ProductDTO>builder()
                .data(response)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PutMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.OK)
    public WebResponse<ProductDTO> rejectedStatusById(@PathVariable Long id) {
        ProductDTO response = approvalService.rejectedStatusById(id);
        return WebResponse.<ProductDTO>builder()
                .data(response)
                .responseCode(HttpStatus.OK.value())
                .responseMessage(HttpStatus.OK.getReasonPhrase())
                .build();
    }
}
