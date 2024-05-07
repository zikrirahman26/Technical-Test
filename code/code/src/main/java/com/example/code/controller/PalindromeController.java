package com.example.code.controller;

import com.example.code.service.PalindromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PalindromeController {

    @Autowired
    private PalindromeService palindromeService;

    @GetMapping("/palindrome/{s}")
    public String getPalindrome(@PathVariable String s) {
        return palindromeService.findPalindrome(s);
    }
}
