package com.example.code.service.impl;

import com.example.code.service.FibonacciService;
import org.springframework.stereotype.Service;

@Service
public class FibonacciServiceImpl implements FibonacciService {
    @Override
    public long calculateFibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("bilangan bulat positif");
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            long a = 1, b = 1, result = 0;
            for (int i = 3; i <= n; i++) {
                result = a + b;
                a = b;
                b = result;
            }
            return result;
        }
    }
}
