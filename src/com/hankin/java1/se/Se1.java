package com.hankin.java1.se;

public class Se1 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;   //i=1
        int j = i++; //j=1 i=2
        int k = i + ++i * i++; // k=11 i=4     。。。

        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
