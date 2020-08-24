package com.hankin.java1.se;
/*
就近原则

变量的分类
    成员变量：类变量（静态的）、实例变量（非静态的）
        修饰符：pubic protected private final static volatile transient
        存储位置：实例变量在：堆
                类变量在：方法区
        作用域： 实例变量：在当前类中"this."（有时this.可以缺省），在其他类中"对象名."访问
                类变量：在当前类中"类名."（有时类名.可以省略）,在其他类中"类名."或"对象名."访问
        生命首期：实例变量：随对象的创建而初始化，随对象的被回收而消亡，每一个对象的实例变量是独立的
                类变量：随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的
    局部变量：（方法体里的，形参，非静态代码块的变量）
        修饰符：只能用final
        存储位置：栈
        作用域：从声名出开始，到所属的}结束
        生命周期：每一个线程，每一次调用都是新的生命周期

非静态代码块的执行：每次创建实例对象都会执行

方法的调用规则：调用一次执行一次
 */
public class Se6 {
    static int s;
    int i;
    int j;
    {
        int i = 1;
        i++; //上面的i 不是 成员变量的i（就近原则）
        j++;
        s++;
    }
    public void test(int j){
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        Se6 se6 = new Se6(); //i = 0 j = 1 s = 1
        Se6 se61 = new Se6();//i = 0 j = 1 s = 2
        se6.test(10); //i = 1 j = 1 s = 3
        se6.test(20); //i = 2 j = 1 s = 4
        se61.test(30);//i = 1 j = 1 s = 5
        System.out.println(se6.i + "," + se6.j + "," + se6.s); //2 1 5
        System.out.println(se61.i + "," + se61.j + "," + se61.s); //1 1 5
    }
}
