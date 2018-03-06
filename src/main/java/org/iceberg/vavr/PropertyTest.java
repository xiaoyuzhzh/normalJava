package org.iceberg.vavr;

import static io.vavr.API.*;

/**
 * Created by xiaoyuzhzh on 1/19/2018.
 */
public class PropertyTest {

    public static void main(String[] args) {
//        Arbitrary<Integer> ints = Arbitrary.integer();
//
//        // square(int) >= 0: OK, passed 1000 tests.
//        Property.def("square(int) >= 0")
//                .forAll(ints)
//                .suchThat(i -> i * i >= 0)
//                .check()
//                .assertIsSatisfied();

        int i = 1;
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );

        System.out.println(1);
    }
}
