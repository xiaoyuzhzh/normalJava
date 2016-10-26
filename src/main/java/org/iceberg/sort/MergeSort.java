package org.iceberg.sort;

import com.sun.scenario.effect.Merge;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by xiaoy on 2016/10/26.
 */
public class MergeSort {

    private static int[] data = new int[10];

    private static int[] output = new int[10] ;

    public static void MergeTwo(int left,int middle,int n){
        int i,j,k,t;//循环变量
        i = left;
        k = left;
        j = middle + 1;//设定数组指针
        while(i<=middle&&j<=n){
            if(data[i]<= data[j]){
                output[k] = data[i];
                i++;
            }
            else{
                output[k] = data[j];
                j++;
            }
            k++;
        }

        //剩余放入数组
        if(i>middle){
            for(t = j; t<=n;t++){
                output[k]=data[t];
                k++;
            }
        }else{
            for(t = i;t<=middle;t++){
                output[k]=data[t];
                k++;
            }
        }

    }

    public static void main(String[] args) {
        int i;//循环变量
        int datalength;//归并序列长度
        int n = 9;

        data = new int[]{0,6,5,4,7,2,8,1,9,0};

        System.out.println("Before merge sorting;"+Arrays.toString(data));

        datalength = 1;
        while (datalength<n){
            System.out.println("Merge sort datalength : " +  datalength);
            MergeAll(n,datalength);//归并
            datalength = 2*datalength;
        }

        System.out.println("Merge sort result:"+Arrays.toString(data));


    }

    public static void MergeAll(int n , int dataLength){
        int i,t;
        i = 0;
        while(i<=(n-2*dataLength-1)){
            MergeTwo(i,i+dataLength-1,i+2*dataLength-1);
            i = i + 2*dataLength;
        }

        if(i+dataLength-1<n){
            MergeTwo(i,i+dataLength-1,n);
        }else{
            for(t = i ; t<=n; t++){
                output[t] = data[t];
            }
        }

        for(t = 0 ; t<=n ; t++){
            data[t] = output[t];
        }

        System.out.println("current sorting result:"+ Arrays.toString(data));


    }




}
