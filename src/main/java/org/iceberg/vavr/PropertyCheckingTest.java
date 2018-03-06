package org.iceberg.vavr;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;

/**
 * Created by xiaoyuzhzh on 2/11/2018.
 */
public class PropertyCheckingTest {

    public static void main(String[] args) {
        Arbitrary<Integer> ints = Arbitrary.integer();


        Property.def("整数的平方大于0")
                .forAll(ints)
                .suchThat(i -> i * i >=0)
                .check()
                .assertIsSatisfied();
    }
}
