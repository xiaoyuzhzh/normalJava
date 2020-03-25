package org.iceberg.convert;



/**
 * Created by xiaoy on 9/12/2017.
 */
public class Impl implements A,B {

    private String str = "Impl";

    public void print(){
        System.out.println(str);
    }

    public void setP(String aa){
        str = aa;
    }

    public Printer getPrinter(){
        return new Printer() {
            @Override
            public void print() {
                System.out.println(str);
            }
        };
    }

    public static A get(){
        Impl impl = new Impl();
        return (A&B)impl;
    }

    public static void main(String[] args) {
        A a = get();
        System.out.println(a);

        Converter converter = Integer::valueOf;
        Integer apply = converter.convert("123");
        System.out.println(apply);

        Impl impl = new Impl();
        Printer printer = impl.getPrinter();
        printer.print();

        impl.setP("sssss");
        printer.print();

        Printer printer1 = () -> {
            System.out.println("bbb");
        };

        printer1.print();
    }
}
