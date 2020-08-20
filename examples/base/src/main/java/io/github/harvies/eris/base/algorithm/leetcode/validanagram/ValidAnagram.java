package io.github.harvies.eris.base.algorithm.leetcode.validanagram;

import io.github.harvies.eris.base.annotations.Complexity;

/**
 * 有效的字母异位词 242
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 解法:可以只利用一个长度为 26 的字符数组，将出现在字符串 s 里的字符个数加 1，而出现在字符串 t 里的字符个数减 1，最后判断每个小写字母的个数是否都为 0。
 */
@Complexity(time = "n", space = "1")
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        //如果字符串长度不同
        if (s.length() != t.length()) {
            return false;
        }
        //使用数组存储
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        //比较每个字母是否都相同,可优化成t--后，判断i是否小于0,提前失败
        for (int i : counter) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

}
