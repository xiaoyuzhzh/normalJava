package org.iceberg.test;

/**
 * Created by xiaoy on 2017/1/19.
 */
public class A {

    public static void a(){
        System.out.println("a");
    }

    public void p(){
        System.out.println("a");
    }

    public static void main(String[] args) {
        String s = null;
        switch (s){
            case "a":
                System.out.println("a");break;
            default:
                System.out.println("b");
        }
    }
}
