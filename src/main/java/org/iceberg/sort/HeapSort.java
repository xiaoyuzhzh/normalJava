package org.iceberg.sort;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by xiaoy on 2016/10/25.
 */
public class HeapSort {

    public static int[] Heap = new int[10];//堆数组

    public static void main(String[] args) {
        int i ;//循环变量
        int index ; //数组下标变量
//        Heap[0]=-1;
//        System.out.println("Please input the values you want to sort(Exit for 0，max 10 number):");
//        index = 1;
//        //读取输入数据，存入数组
//        InputStreamReader is = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(is);
//        StringTokenizer st;
//        do{
//            System.out.print("Data"+index+":");
//            try {
//                String myline = br.readLine();
//                st = new StringTokenizer(myline);
//                Heap[index] = Integer.parseInt(st.nextToken());//取得输入值
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            index++;
//        }while (Heap[index-1]!=0);

        Heap = new int[]{0,6,5,4,7,2,8,1,9,0};
        index = 10;

        System.out.print("Before Heap Sorting:");

        for(i = 1;i<index-1;i++){
            System.out.print(""+Heap[i]+"");
        }

        System.out.println("");
        HeapSort(index-2);

        System.out.println("After Heap Sorting:");
        for(i = 1 ; i<index-1;i++){
            System.out.print(""+Heap[i]+"");
        }
        System.out.println("");
    }

    public static void CreateHeap(int root,int index){
        int i,j;//循环变量
        int temp;//暂存变量
        int finish;//判断是否完成

        j = 2*root;//子节点的index
        temp = Heap[root];//暂存堆的root值
        finish = 0;//预设堆尚未完成

        while (j<=index&&finish == 0){
            if(j<index){//找到最大的子节点
                if(Heap[j]<Heap[j+1]){
                    j++;
                }
            }

            if(temp>=Heap[j]){
                finish = 1;//堆建立完成
            }else{
                Heap[j/2]=Heap[j];//父节点=目前节点
                j=2*j;
            }
        }

        Heap[j/2]=temp;//父节点=root值
    }


    public static void HeapSort(int index){
        int i,temp;
        //将二叉树转换成堆
        for(i=(index/2);i>=1;i--){
            CreateHeap(i,index);
        }

        System.out.println("First heap finished :"+Arrays.toString(Heap));

        //开始进行堆排序
        for(i=(index-1);i>=1;i--){
            temp = Heap[i+1];
            //堆的root值和最后一个值交换
            Heap[i+1] = Heap[1];
            Heap[1] = temp;
            CreateHeap(1,i);//对其余数值重建堆
            //打印堆的处理过程
            System.out.println("Sorting Processing:");
            System.out.println(Arrays.toString(Heap));
        }
    }
}
