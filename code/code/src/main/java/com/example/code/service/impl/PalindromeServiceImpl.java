package com.example.code.service.impl;

import com.example.code.service.PalindromeService;
import org.springframework.stereotype.Service;

@Service
public class PalindromeServiceImpl implements PalindromeService {
    @Override
    public String findPalindrome(String s) {
        String longestSubstring = "";
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String currentSubstring = s.substring(i, j);
                if (!isPalindrome(currentSubstring) && currentSubstring.length() > longestSubstring.length()) {
                    longestSubstring = currentSubstring;
                }
            }
        }

        return longestSubstring;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}