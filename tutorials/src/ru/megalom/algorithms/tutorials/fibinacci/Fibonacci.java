package ru.megalom.algorithms.tutorials.fibinacci;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        long start = 0L;
        long end = 0L;
        System.out.println("Числа фибоначчи при n=3,n=5,n=10:\n");
        showResult("цикл","fibCycle",3,5,40);
        System.out.println();
        showResult("рекурсию","fibRec",3,5,40);
        System.out.println();
        showResult("оптимизированную рекурсию","fibRecOpt",3,5,40);
    }


    private static void showResult(String methodLabel,String methodName, int ... params){
        try {


            System.out.print("Способ через "+methodLabel+": ");
            long start = System.currentTimeMillis();

            for(int i=0;i< params.length;i++){
                long number =0;
                if(methodName=="fibRecOpt"){
                    java.lang.reflect.Method method =Fibonacci.class.getDeclaredMethod(methodName, int.class,long[].class);
                    long[] mem = new long[params[i]+1];
                    Arrays.fill(mem,-1);
                    number=(long)method.invoke(null,params[i],mem);
                }
                else {
                    java.lang.reflect.Method method =Fibonacci.class.getDeclaredMethod(methodName, int.class);
                    number = (long) method.invoke(null, params[i]);
                }

                System.out.print(number);
                if(i<params.length-1)
                    System.out.print(", ");
            }
            System.out.print(".\n");

            long end = System.currentTimeMillis();
            System.out.println("Время выполнения метода: "+(end-start)+"ms");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    //better algorithm with cycle O(n+n)=O(2n)=O(n)
    private static long fibCycle(int n){
        long result = 0;
        if(n==1)
            result=1;
        if(n>1){
            long a=0;
            long b=1;
            // O(n)
            for(int i = 2;i<=n;i++){
                result=a+b;
                a=b;
                b=result;
            }
        }
        return result;
    }

    //too slow algorithm with recursion
    // O(2^n)
    private static long fibRec(int n){
        if(n<=1)
            return n;
        return fibRec(n-1)+fibRec(n-2);
    }

    // optimized algorithm with recursion O(n)
    private static long fibRecOpt(int n,long mem[]){
        if(mem[n]!=-1)
            return mem[n];
        if(n<=1)
            return n;
        long result = fibRecOpt(n-1,mem)+fibRecOpt(n-2,mem);
        mem[n]=result;
        return result;
    }
}
