package org.iceberg.vavr;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;

/**
 * Created by xiaoyuzhzh on 1/18/2018.
 */
public class FunctionTest {

    static Function1<String,Option<Integer>> string2Int = Function1.lift (i -> Integer.parseInt(i));

    static Function0 getFunction(){
        int i = 1;
        return () -> 1;
    }

    public static void main(String[] args) {
//        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> {
//            System.out.println("a = " + a);
//            System.out.println("b = " + b);
//            System.out.println("c = " + c);
//            return a + b + c;
//        };
//        final Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(3);
//        Function1<Integer, Integer> add1 = add2.apply(2);
//        int result = add2.apply(1).apply(2);
//        System.out.println(result);
//
        Function2<Integer,Integer,Integer> sum2 = (a,b)->{
            System.out.println("in:"+a+","+b);
            return a + b;
        } ;
        Function2<Integer, Integer, Integer> cache = sum2.memoized();//这个函数会缓存入参相同的结果
        System.out.println(cache.apply(1,2));
        System.out.println(cache.apply(1,3));
        System.out.println(cache.apply(1,2));


//        String str = "1";
//        String str2 = "sss";
//        Option<Integer> int1 = string2Int.apply(str);
//        System.out.println(int1.getOrElse(0));
//        Option<Integer> int2 = string2Int.apply(str2);
//        System.out.println(int2.getOrNull());

        Function0 f1 = getFunction();
        Function0 f2 = getFunction();
        System.out.println(f1 == f2);
    }
}
