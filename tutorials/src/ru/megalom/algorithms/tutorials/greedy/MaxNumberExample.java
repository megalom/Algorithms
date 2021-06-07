package ru.megalom.algorithms.tutorials.greedy;

import java.util.Arrays;

// Пример жадного алгоритма для нахождения наибольшего числа из заданного набора цифр
public class MaxNumberExample {
    public static void main(String[] args) {
        String digits="49683659476";
        System.out.println("Максимально возможное число из набора цифр(метод пузырька): "+maxNumberBuble(digits));
        System.out.println("Максимально возможное число из набора цифр(быстрая сортировка): "+maxNumberQuick(digits));
    }
    // Нахождение при помощи пузырьковой сортировки - не эффективно
    // O= O(n^2)
    public static String maxNumberBuble(String digits){
        StringBuilder maxNumber=new StringBuilder();
        StringBuilder digitsString=new StringBuilder(digits);

        while(digitsString.length()>0) {
            int currentMaxDigitIndex=0;
            for (int i = 0; i < digitsString.length(); i++) {
                if(digitsString.charAt(currentMaxDigitIndex)<digitsString.charAt(i)) {
                    currentMaxDigitIndex=i;
                }
            }
            maxNumber.append(digitsString.charAt(currentMaxDigitIndex));
            digitsString.deleteCharAt(currentMaxDigitIndex);
        }


        return maxNumber.toString();
    }
    // Быстрая сортировка - более предпочтительный вариант
    // O(nlog(n))
    public static String maxNumberQuick(String digits) {
        StringBuilder maxNumber=new StringBuilder();
        int[] digitsArray= new int[digits.length()];

        //O(n)
        for(int i =0;i<digits.length();i++) {
            digitsArray[i] = Integer.parseInt(String.valueOf(digits.charAt(i)));
        }

        //O(nlog(n)
        Arrays.sort(digitsArray);

        //O(n)
        for(int i=digitsArray.length-1; i>=0;i--)
            maxNumber.append(digitsArray[i]);
        return maxNumber.toString();
    }

}
