package com.algo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

import java.util.*;

public class SubSum {

    public boolean dpSubSum(Set<Integer> nums, Integer sum){
        Long startTime=System.currentTimeMillis();
        Set<Integer> sumSet= Sets.newHashSet();
        for(Integer num : nums){
            Set<Integer> newSet=Sets.newHashSet();
            newSet.add(num);
            for(Integer item: sumSet){
                newSet.add(item+num);
            }
            sumSet.addAll(newSet);
        }
        boolean result=sumSet.contains(sum);
        System.out.println("time consumed: "+(System.currentTimeMillis()-startTime));
        return result;
    }

    public boolean recursiveSubSum(Integer[] nums, int endIndex, Integer sum){
        Integer lastNum=nums[endIndex];
        if(sum==0 || sum-lastNum==0)
            return true;
        if(endIndex==0)
            return false;

        boolean result= recursiveSubSum(nums, endIndex-1, sum) ;
        if(!result && sum>lastNum)
            result=recursiveSubSum(nums, endIndex-1, sum-lastNum);
        return result;

    }
    public static void main(String[] args){
        ArrayList<Integer> numList=new ArrayList(Arrays.asList(3, 34, 4, 12, 5, 2, 50));
        Set<Integer> nums = new HashSet(numList);
        Integer sum = 59;
        SubSum subSum=new SubSum();
        System.out.println(subSum.dpSubSum(nums, sum));
        Integer[] numsArray=new Integer[]{3, 34, 4, 12, 5, 2,50};
        Long startTime=System.currentTimeMillis();
        boolean result=subSum.recursiveSubSum (numsArray, numsArray.length-1, sum);
        System.out.println(result+ ", time consumed: "+(System.currentTimeMillis()-startTime));

    }
}
