package com.tiny.lang.java.interview;

/**
 * @author tiny.wang
 */
public class StockBuyStrategy {
    public static void main(String[] args) {
        int[] prices = new int[]{4, 3, 2, 1};
        System.out.println(findMaxProfit(prices));
        System.out.println(findMaxProfitInMultiTransMode(prices));

        prices = new int[]{4, 2, 6, 5, 9, 1, 10};
        System.out.println(findMaxProfit(prices));
        System.out.println(findMaxProfitInMultiTransMode(prices));

    }

    private static int findMaxProfitInMultiTransMode(int[] prices) {
        if (prices.length < 2){
            return 0;
        }
        /// find first smaller
        int begin = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] >= prices[i+1]){
                begin = i + 1;
            }else {
                break;
            }
        }

        // find next elem before smaller
        int indexBegin = begin;
        int totalProfit = 0;
        while (indexBegin < prices.length){
            int indexEnd = findElemIndexBeforeSmall(indexBegin, prices);
            totalProfit += prices[indexEnd] - prices[indexBegin];
            indexBegin = indexEnd + 1;
        }
        return totalProfit;
    }
    private static int findElemIndexBeforeSmall(int begin, int[] prices){
        int index = begin;
        while (index < prices.length - 1){
            if (prices[index] < prices[index + 1]){
                index++;
            }else {
                break;
            }
        }
        return index;
    }

    private static int findMaxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // find first small one
        int begin = 0;
        while (begin < prices.length - 1) {
            if (prices[begin] >= prices[begin + 1]) {
                begin++;
            } else {
                break;
            }
        }

        int maxTotal = 0;
        do {
            int maxItem = findNextMaxValue(begin, prices);
            int maxDelta = maxItem - prices[begin];
            if (maxDelta > maxTotal) {
                maxTotal = maxDelta;
            }
        } while (++begin < prices.length);
        return maxTotal;
    }

    private static int findNextMaxValue(int begin, int[] prices) {
        int max = 0;
        for (int i = begin; i < prices.length; i++) {
            if (max < prices[i]) {
                max = prices[i];
            }
        }
        return max;
    }

}
