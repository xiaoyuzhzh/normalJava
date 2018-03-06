package org.iceberg.test;

/**
 * Created by xiaoy on 2017/1/19.
 */
public class B extends A {

    public void p(){
        System.out.println("b");
    }

    public static void main(String[] args) {
        A a = new B();
        a.p();

        B.a();
    }
}
