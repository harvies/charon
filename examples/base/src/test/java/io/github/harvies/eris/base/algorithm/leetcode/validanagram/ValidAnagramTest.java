package io.github.harvies.eris.base.algorithm.leetcode.validanagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidAnagramTest {

    @Test
    void isAnagram() {
        ValidAnagram validAnagram = new ValidAnagram();
        assertTrue(validAnagram.isAnagram("anagram", "nagaram"));
        assertFalse(validAnagram.isAnagram("rat", "car"));
    }
}