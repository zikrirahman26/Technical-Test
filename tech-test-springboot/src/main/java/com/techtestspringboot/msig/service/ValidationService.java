package com.techtestspringboot.msig.service;

public interface ValidationService {

    void validate(Object request);

    void validateStatusChange(String currentStatus, String newStatus);

    }
