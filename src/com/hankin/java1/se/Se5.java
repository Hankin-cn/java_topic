package com.hankin.java1.se;
/*
编程题：有n步台阶，一次只能上1步或2步，共有多少种走法。
 */
public class Se5 {
    public static void main(String[] args) {
        /*long start = System.currentTimeMillis();
        System.out.println(f(40));
        long end = System.currentTimeMillis();
        System.out.println(end - start);*/
        System.out.println("-------------------");
        long start1 = System.currentTimeMillis();
        System.out.println(loop(40));
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }

/*
递归：减少的代码量，代码精简，可读性好，但是递归浪费了空间，而且递归太深容易造成堆栈的溢出。
    n = 1   f(1) = 1
    n = 2   f(2) = 2 一步一步/直接2步
    n = 3   先f(1),然后从f(1)直接跨2步
            先f(2),然后从f(2)跨一步
            f(3) = f(1) + f(2)
    n = 4   先f(2),然后从f(2)直接跨2步
            先f(3)，然后从f(3)跨一步
            f(4) = f(2) + f(3)
    .
    n = x   f(x) = f(x-2) + f(x-1)
*/
    public static int f(int n){
        if (n < 1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2){
            return n;
        }
        return f(n-2) + f(n-1);
    }

    /*
    循环迭代:代码不够简介，可读性差，但是效率高。
     */
    public static int loop(int n){
        if (n < 1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2){
            return n;
        }
        int one = 2;//走到第二级台阶的走法
        int two = 1; //走到第一级台阶的走法
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            //最后跨两步 + 最后跨一步的走法
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
}

