package org.iceberg.gc;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by xiaoy on 2016/10/30.
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC HOOK = null;

    public  void isAlive(){
        System.out.println("alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("finalize method executed");

        FinalizeEscapeGC.HOOK = this;
    }


    public static void main(String[] args) throws InterruptedException {
        HOOK  = new FinalizeEscapeGC();

        //first gc
        HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(HOOK!=null)
            HOOK.isAlive();
        else
            System.out.println("dead");


        //second gc
        HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(HOOK!=null)
            HOOK.isAlive();
        else
            System.out.println("dead");
    }
}
