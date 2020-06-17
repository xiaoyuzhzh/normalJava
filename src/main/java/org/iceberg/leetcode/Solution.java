package org.iceberg.leetcode;
/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 */

import java.util.HashSet;
import java.util.Set;

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        char[] chars = s.toCharArray();
        int length = 0;
        Set<Character> characterSet = new HashSet<Character>();
        int start = 0;
        int end = 0;
        for (; end < chars.length; end++) {
            if(characterSet.contains(chars[end])){
                //将start游标移动到第一个chars[end]出现的位置之后,并且从characterSet中移除之前的元素
                for(; chars[start] != chars[end];start ++){
                    characterSet.remove(chars[start]);
                }
                start ++;
            }else{
                characterSet.add(chars[end]);
                length = Math.max(length,end-start+1);
            }
        }
        return length;
    }

}


// @lc code=end
