package ru.megalom.algorithms.tutorials.search;

import java.util.Arrays;

// Пример алгоритма двоичного поиска со сложностью O(log(N))
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={5,8,3,6,4,1,10,15,13,41,32,7,21,11,30,27};
        int key = 10;
        Arrays.sort(array);
        System.out.println("На входе массив: "+Arrays.toString(array));
        System.out.println("Индекс искомого элемента("+key+") = "+binarySearch(array,key));
        System.out.println("Индекс искомого элемента("+key+
                ") с использованием рекурсии = "+
                binarySearchRecursion(array,key,0, array.length-1));
    }
    // С использование цикла
    public static int binarySearch(int[] array, int key){
        int index=0;
        int start=0;
        int end= array.length-1;

        while(start<=end){
            index=start+(end-start)/2;
            if(array[index]<key)
                start=index+1;
            if(array[index]>key)
                end=index-1;
            if(array[index]==key)
                break;
            if(start>=end)
                index=-1;
        }
        return index;
    }

    // С использованием рекурсии
    public static int binarySearchRecursion(int[] array,int key,int start,int end){
        if(start>=end)
            return -1;
        int index=start+(end-start)/2;
        if(array[index]>key)
            end=index-1;
        if (array[index]>key)
            start=index+1;
        if(array[index]==key)
            return index;
        return binarySearchRecursion(array,key,start,end);
    };
}
