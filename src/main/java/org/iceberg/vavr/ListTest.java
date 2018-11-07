package org.iceberg.vavr;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import io.vavr.collection.Stream;

/**
 * Created by xiaoyuzhzh on 2/11/2018.
 */
public class ListTest {



    public static void main(String[] args) throws UnsupportedEncodingException {
//        Integer integer = Stream.from(1).filter(i -> i % 2 == 0).get();
//        System.out.println(integer);

//        List<Object> objects = Collections.unmodifiableList(new LinkedList<>());
//        objects.add(1);

        io.vavr.collection.List<Integer> list1 = io.vavr.collection.List.of(1, 2, 3);
        io.vavr.collection.List<Integer> list2 = list1.tail().prepend(0);

        System.out.println(list1.asJava());
        System.out.println(list2);

        String s = list1.map(integer -> integer.toString()).intersperse(",").foldLeft(new StringBuilder(), StringBuilder::append).toString();
        System.out.println(s);


        System.out.println(list1.mkString("[",",","]"));

        String str = "中";

        System.out.println(Arrays.toString(str.getBytes("utf-8")));


    }
}
