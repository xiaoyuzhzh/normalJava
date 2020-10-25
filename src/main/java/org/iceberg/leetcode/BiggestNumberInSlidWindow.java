package org.iceberg.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Tags
 * Companies
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * Created by xiaoyuzhzh on 2020/10/24.
 */
public class BiggestNumberInSlidWindow {

    public static void main(String[] args) {
        int[] source = new int[]{1,3,-1,-3,5,3,6,7};
        int[] biggestNumberArray = getBiggestNumberArray(source, 3);
        System.out.println(Arrays.toString(biggestNumberArray));
    }


    /**
     * window 是一个单调递减的窗口
     * @param source
     * @param k
     * @return
     */
    public static int[] getBiggestNumberArray(int[] source,int k){
        if(source.length<=k){
            //todo 返回数组中最大的值
        }
        ArrayDeque<Integer> window = new ArrayDeque<>();
        int[] result = new int[source.length-k+1];
        //初始化window
        for (int i = 0 ; i< k; i ++){
            while (!window.isEmpty()&&source[window.getLast()]<source[i]){
                window.removeLast();
            }
            window.addLast(i);
        }

        result[0] = source[window.getFirst()];
        //开始从window中取出最大值
        for(int i = k ;i<source.length;i++){
            //超出范围的头元素弹出
            if(window.getFirst() == (i-k)){
                window.removeFirst();
            }
            while (!window.isEmpty()&&source[window.getLast()]<source[i]){
                window.removeLast();
            }
            window.addLast(i);
            result[i-k+1] = source[window.getFirst()];
        }

        return result;
    }

}
