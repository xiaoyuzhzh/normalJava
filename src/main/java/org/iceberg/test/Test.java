package org.iceberg.test;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoy on 2016/12/12.
 */
public class Test {

    /**
     * 长度为10的数组，数字为20以内正整数
     * O(N)排序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{3,4,13,2,5,3,10,19,1,17};
        int[] result = sort(arr);
        System.out.println(Arrays.toString(result));
        Object[] objects = new Object[10];
        ArrayList[] arrayLists = new ArrayList[10];
    }

    public static int[] sort(int[] arr){
        int[] re = new int[10];
        List<List<Integer>> temp = new ArrayList<>(20);
        for(int i = 0 ; i< 20; i ++){
            temp.add(new LinkedList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            List<Integer> integers = temp.get(arr[i]);
            integers.add(arr[i]);
        }
        int j = 0;
        for (List<Integer> integers : temp) {
            if(!CollectionUtils.isEmpty(integers)){
                for (Integer integer : integers) {
                    re[j] = integer;
                    j++;
                }
            }
        }
        return re;
    }
}
