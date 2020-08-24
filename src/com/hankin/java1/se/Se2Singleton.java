package com.hankin.java1.se;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.*;

/*
*   单例设计模式
*     只能有一个实例
*       构造器私有化
*     必须自行创建
*       含有一个给累的静态变量来保存这个唯一的实例
*     必须想整个系统提供这个实例
*       对外提供获取该实例对象的方式：
*           1直接暴露 2用静态变量的get方法获取
 */
public class Se2Singleton {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Singleton1 instance = Singleton1.INSTANCE;
        System.out.println(instance);
        Singleton2 instance1 = Singleton2.INSTANCE;
        System.out.println(instance1);
        Singleton3 instance2 = Singleton3.INSTANCE;
        System.out.println(instance2.toString());
       /* System.out.println("------单线程------");
        Single4 s1 = Single4.getInstance();
        Single4 s2 = Single4.getInstance();
        System.out.println(s1 == s2);*/
        System.out.println("------多线程------");
        Callable<Single5> c = new Callable<Single5>() {
            @Override
            public Single5 call() throws Exception {
                return Single5.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Single5> submit = es.submit(c);
        Future<Single5> submit1 = es.submit(c);

        Single5 single4 = submit.get();
        Single5 single41 = submit1.get();
        es.shutdown();
        System.out.println(single4 == single41);
    }
}
/*
    饿汉式:
      直接创建实例对象
 */
class Singleton1{
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1(){

    }
}
//1.5后 用枚举可以创建单例对象
enum Singleton2{
    INSTANCE
}
//静态代码块的方式(用到加载配置文件时)
class Singleton3{
    public static final Singleton3 INSTANCE;

    private String single;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("single.properties"));
            INSTANCE = new Singleton3(properties.getProperty("single"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Singleton3(String single){
        this.single = single;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "single='" + single + '\'' +
                '}';
    }

    public static Singleton3 getINSTANCE() {
        return INSTANCE;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }
}
//懒汉式
//单线程下安全
class Single4{
    private static Single4 instance; //私有化
    private Single4(){}
    public static Single4 getInstance() throws InterruptedException {
        if (instance == null){
            Thread.sleep(5000);
            instance = new Single4();
        }
        return instance;
    }
}
//多线程下安全
class Single5{
    private static Single5 instance; //私有化
    private Single5(){}
    public static Single5 getInstance() throws InterruptedException {
        if (instance == null) { //如果已经有了，就不用使用同步了
            synchronized (Single5.class) {
                if (instance == null) {
                    Thread.sleep(5000);
                    instance = new Single5();
                }
            }
        }
        return instance;
    }
}
//多线程安全++++
/*
在内部类加载和初始化的，才创建INSTANCE对象
静态内部类，不会随着外部类的加载初始化而初始化，他是单独期去加载和初始化的
因为是在内部类加载和初始化时才创建的，因此是线程安全的。
 */
class Single6{
    private Single6(){}
    private static class Inner{
        private static final Single6 INSTANCE = new Single6();
    }
    public static Single6 getInstance(){
        return Inner.INSTANCE;
    }
}