package org.iceberg.vavr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.vavr.control.Try;

/**
 * Created by xiaoy on 9/11/2017.
 */
public class Test {

    public static Try<Integer> divide(Integer dividend,Integer divisor){
        int divide = 0;
        return Try.of(()->divide/divisor);
    }

    public static String divide(){
        Ob ob = new Ob();
        return Try.of(()->{
//            throw new RuntimeException();
            return ob.toString();
        }).get();
    }

    static class Ob {
        int a = 0;
    }

    static List testInvoke(TestM<Long> testM){
        return testM.getR();
    }

    public static void main(String[] args) {
        System.out.println(testInvoke(()->new LinkedList<Long>()));

        List<Long> l = new ArrayList<>();
        System.out.println(testInvoke(new TestM<Long>() {
            @Override
            public List<Long> getR() {
                return l;
            }
        }));
    }

    interface TestM<T>{
        List<T> getR();
    }

}
