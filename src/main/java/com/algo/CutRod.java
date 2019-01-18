package com.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CutRod {
    public static void main(String args[])
    {
        int len[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        int value[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = value.length;
        System.out.println("Maximum Obtainable Value is "+
                cutRod(value, size));
        System.out.println("Maximum Obtainable Value is "+
                recursiveCutRod(len, value, 8));
    }

    private static int cutRod(int[] arr, int size) {
        return 0;
    }

    private static int recursiveCutRod(int[] len, int[] value, int size){
        List<Integer> tmpList=new ArrayList<>();
        for(int i=0;i<len.length;i++){
            if(size>=len[i]) {
                int new_size = size - len[i];
                int new_value = value[i] + recursiveCutRod(len, value, new_size);
                tmpList.add(new_value);
            }else
                return 0;
        }
        return Collections.max(tmpList);
    }
}
