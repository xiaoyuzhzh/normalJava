package org.iceberg.vavr;

import io.vavr.control.Either;

/**
 * Created by xiaoyuzhzh on 2/9/2018.
 */
public class EitherTest {
    public static void main(String[] args) {
        Either<String,Integer> value = compute().right().map(i -> i * 2).toEither();
        System.out.println(value.getOrElseGet(a->{
            System.out.println(a);
            return 1;
        }));

        Either.LeftProjection<String, Integer> left = value.left().map(i -> "changed");
        System.out.println(left.toEither().right().get());

    }

    private static Either<String, Integer> compute() {
        Either<String, Integer> left = Either.right(1);
//        Either<String, Integer> left = Either.left("error");

        return left;
    }
}
