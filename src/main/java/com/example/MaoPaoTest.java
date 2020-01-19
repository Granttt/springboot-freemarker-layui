package com.example;

import java.util.Arrays;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/24 21:08
 * @Description:冒泡排序
 * https://blog.csdn.net/wddygbk/article/details/78704089
 * https://blog.csdn.net/qq_26173219/article/details/80221424
 */
public class MaoPaoTest {
    public static void main(String[] args) {
        int[] arr={9,8,5,6,7,4};
        for (int j = 0; j < arr.length-1; j++) {//第一层循环，控制排序的次数
            for (int i = 0; i < arr.length-1; i++) {////第二层循环，控制排序
                if(arr[i]>arr[i+1]){//判断第一个数和它的下一个数的大小
                    int m=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=m;//如果第一个数大，则把第一个数的和第二个数交换位置，然后用交换后的数继续和下一个数比较，以此类推
                }
            }
        }
        System.out.println(Arrays.toString(arr));//循环输出排序后的数
    }
}