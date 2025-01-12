package com.techtestspringboot.msig.service.impl;

import com.techtestspringboot.msig.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private Validator validator;

    @Override
    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    public void validateStatusChange(String currentStatus, String newStatus) {
        if ("Approved".equals(currentStatus)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is already approved");
        }
        if ("Rejected".equals(currentStatus) && "Approved".equals(newStatus)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rejected product cannot be approved");
        }
    }
}
