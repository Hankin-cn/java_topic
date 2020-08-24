package com.hankin.java1.se;

public class Se3 {

}
/*
* 类初始化
*   》一个类要创建实例需要先加载并初始化该类
*       *main方法所在的类需要先加载和初始化
*   》一个子类初始化会先初始化夫类
*   》一个类初始化就是执行了<clinit>()方法
*       *<clinit>()方法是由静态变量显示赋值代码和静态代码块组成
*       *类变量显示赋值代码和静态代码块代码从上到下顺序执行
*       *<clinit>()方法只执行一次
* 实例初始化
*   》实例初始化就是执行<init>()方法
*       *<init>()方法可能重载有多个，有几个构造器就有几个<init>方法
*       *init>()由 非 静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
*       *非静态实例变量显示赋值和非静态代码块从上到下顺序执行，对应构造器的代码最后执行
*       *每创建实例对象，调用对应构造器，执行的就是对应的init>()方法
*       *init>()方法的首行是super（）或super（实参列表），即对应父类的init>()方法
* 方法重写
*   》哪些方法不可以被重写
*       *final方法
*       *static方法
*       *private等子类不可见的方法
*   》对象的多态
*       *子类重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
*       *非静态方法的默认调用对象是this
*       *this对象在构造器或者说<init>方法中就是正在创建的对象
 */

/**
 * 父类的初始化<clinit> 只执行一次
 *     （1）j = method();（按顺序）
 *     （2）父类的静态代码块（按顺序）
 *
 * 父类的实例化方法：
 *      （1）super（）（最前）
 *      （2）i = test（）（按顺序）
 *      （3）子类的非静态代码块（按顺序）
 *      （4）子类的无参构造（最后）
 *
 * 非静态方法前面有一个默认的对象this
 * this在构造器里，他表示的是正在创建的对象，因为这里是在创建子类对象，
 * 所以test这里执行的是子类重写的代码（面向对象多态）
 *
 * 这里i = test执行的是子类重写的test方法
 */
class Father{
    private int i = test();
    private static int j = method();
    static {
        System.out.print("(1)");
    }
    Father(){
        System.out.print("(2)");
    }
    {
        System.out.print("(3)");
    }
    public int test(){
        System.out.print("(4)");
        return 1;
    }
    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}

/**
 * 子类的初始化<clinit> 只执行一次
 *     （1）j = method();（按顺序）
 *     （2）子类的静态代码块（按顺序）
 * 先初始化父类(5)(1)
 * 初始化子类(10)(6)
 *
 * 子类的实例化方法：
 *      （1）super（）（最前）  (9)(3)(2)
 *      （2）i = test（）（按顺序）   (9)
 *      （3）子类的非静态代码块（按顺序）(8)
 *      （4）子类的无参构造（最后）(7)
 *
 * 因为创建了两Son对象，因此实例话方法执行两次
 * (9)(3)(2)(9)(8)(7)
 */
class Son extends Father{
    private int i = test();
    private static int j = method();
    static {
        System.out.print("(6)");
    }
    Son(){
        //super(); //在子类个构造器中一定会调用父类的构造器
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }
    public int test(){
        System.out.print("(9)");
        return 1;
    }
    public static int method(){
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}