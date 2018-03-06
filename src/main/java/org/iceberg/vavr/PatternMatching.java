package org.iceberg.vavr;

import com.google.common.base.Function;

import java.util.Date;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.*;
import static io.vavr.Predicates.instanceOf;
import static io.vavr.API.Match.*;

/**
 * Created by xiaoyuzhzh on 2/11/2018.
 */
public class PatternMatching {

    public static void main(String[] args) {
//        Arbitrary<Integer> ints = Arbitrary.integer();
//
//        int a = -1;
//
//        String of = Match(a).of(
//                Case($(1), "one"),
//                Case($(i -> i > 0), "positive"),
//                Case($(isIn(2,3)),i -> (i+i)+""),
//                Case($(), PatternMatching::printError)
//        );
//
//        System.out.println(of);

//        Object d = new Date();
//
//        System.out.println(        Match(d).of(
//                Case($(instanceOf(String.class)),i-> i),//i 自动转型为string
//                Case($(i -> i instanceof Double), i -> ((Function<Double, String>) (PatternMatching::aa)).apply((Double)i)),//自己写表达式则不会转型,需要手动转型
////                Case($(instanceOf(Double.class)), i -> ((Function<Double, String>) (PatternMatching::aa)).apply(i)),//实例的方法可以直接使用instanceOf
////                Case($((Predicate<Double>) (i -> i instanceof Double)), i -> ((Function<Double, String>) (PatternMatching::aa)).apply(i)),//可以使用predicate指定泛型
//                Case($(),(Function1<Object,String>)i-> i.toString())//通配的执行表达式需要声明好泛型，否则编译不通过
//        ));
//
//
//        Try<Tuple2<Integer,String>> _try = Try.of(() -> Tuple.of(1,"2"));
//
//        Object of = Match(_try).of(
//                Case($Success($Tuple2($(1),$("a"))), value -> value),
//                Case($Failure($()), x -> x),
//                Case($(),"1")
//        );
//
//        System.out.println(of);


//        String a = "a";
//
//        Pattern1<Integer, Integer> p2 = Pattern1.of(Integer.class, $(1), i -> new Tuple1<>(i));
//        Pattern1<String, Integer> p1 = Pattern1.of(String.class, p2, i -> "a".equalsIgnoreCase(i)?Tuple.of(1):Tuple.of("Error"));//Pattern1.of方法有三个参数，第一个是类型需求匹配，第二个是嵌套的匹配表达式，第三个是类型匹配之后执行的函数
//        Match(a).of(
//                Case(p1, i ->{
//                    System.out.println(1);
//                    return null;
//                }
//                ),
//                Case($(),i->{
//                    System.out.println(2);
//                    return null;})
//        );
//

        // 可变的值可能会被匹配表达式所改变，需要注意
        ValueT valueT = new ValueT();
        valueT.value = 2;

        Match(valueT).of(
                Case($(i-> {((ValueT)i).value = 1;return true;}),i -> i)
        );

        System.out.println(valueT.value);//1


    }

    public static String printError(){
        return "error number";
    }

    public static String aa(double a){
        System.out.println(a*a);
        return "nothing";
    }

    static class ValueT{
        public int value = 0;
    }
}
