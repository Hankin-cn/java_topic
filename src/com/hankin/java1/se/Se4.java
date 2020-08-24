package com.hankin.java1.se;

import java.util.Arrays;
/*
* 方法的传参机制
*   1形参是 基本数据类型 是 数据值
*   2行参是 引用数据类型 是 地址值
*       特殊的类型：String、包装类等对象不可变
 */
public class Se4 {
    public static void main(String[] args) {
        int i  = 1;
        String str = "hello";
        Integer num = 2;
        int[] arr = {1,2,3,4,5};
        MyData my = new MyData();

        change(i,str,num,arr,my);

        System.out.println("i = "+ i);  //1
        System.out.println("str =" + str); //hello
        System.out.println("num = "+ num); //2
        System.out.println("arr = " + Arrays.toString(arr)); //2,2,3,4,5
        System.out.println("my.a = " + my.a); //11
    }
    public static void change(int j, String s, Integer n, int[] a, MyData m){
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}
class MyData{
    int a = 10;
}
