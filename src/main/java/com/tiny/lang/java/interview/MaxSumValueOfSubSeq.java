package com.tiny.lang.java.interview;

/**
 * @author tiny
 */
public class MaxSumValueOfSubSeq {
    public static void main(String[] args) {
        var arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int subSeqSum = 0;
        int total = 0;
        for (int elem : arr) {
            subSeqSum = Math.max(subSeqSum + elem, elem);
            total = Math.max(total, subSeqSum);
        }
        System.out.println(total);
    }
}
