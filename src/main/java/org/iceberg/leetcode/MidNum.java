package org.iceberg.leetcode;
/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
class MidNum {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double midNum = 0;
        if (nums1.length == 0) {
            midNum = getMidNum(nums2);
            return midNum;
        }
        if (nums2.length == 0) {
            midNum = getMidNum(nums1);
            return midNum;
        }

        //先求出中位数
        int totalLength = nums1.length + nums2.length;
        int midIndex;//第一个中位数下标
        if (totalLength % 2 == 0) {
            midIndex = totalLength / 2 - 1;
        } else {
            midIndex = totalLength / 2;
        }

        //开始遍历数组
        int current = 0;
        int index1 = 0;
        int index2 = 0;
        int first = 0;
        int second =0;
        //一直遍历到除了两个中位数
        for (; current <= midIndex + 1; current++) {

            //只需要记录归并队列尾部的数据就行了
            if (index1 < nums1.length && index2< nums2.length && nums1[index1] <= nums2[index2]) {
                second = nums1[index1];
                index1++;
            } else if (index2 < nums2.length) {
                second = nums2[index2];
                index2++;
            } else if( index1 < nums1.length){
                second = nums1[index1];
                index1++;
            }

            if(current == midIndex) {
                //记录下第一位中位数
                first = second;
            }

        }

        if(totalLength %2 == 0){
            midNum = ((double) first + second)/2;
        }else {
            midNum = first;
        }

        return midNum;
    }

    private double getMidNum(int[] nums2) {
        if (nums2.length % 2 == 0) {
            return ((double) (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1])) / 2;
        }
        return nums2[nums2.length / 2];
    }

    public static void main(String[] args) {
        double medianSortedArrays = new MidNum().findMedianSortedArrays(new int[]{1,3}, new int[]{2});
        System.out.println(medianSortedArrays);
    }
}
// @lc code=end

