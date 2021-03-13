package io.github.harvies.charon.tests.base.algorithm.leetcode.p13;

public class RomanToInteger {
    public static void main(String[] args) {

        String a = "LVIII";
        
    }

    private int romanStrToInt(String roman) {
//        roman.
        return 0;
    }

    private int getValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }


    }
}
