package org.iceberg.script.js;

import com.alibaba.fastjson.JSON;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by xiaoyuzhzh on 6/8/2018.
 */
public class JsTest
{

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("d:/test.js"));

        Invocable invocable = (Invocable) engine;
        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());

        invocable.invokeFunction("fun2", new Date());
        invocable.invokeFunction("fun2", LocalDateTime.now());
//        invocable.invokeFunction("fun2", new Person());
        Object fun3Result = invocable.invokeFunction("fun3", JSON.toJSONString(new Obj1()));
        System.out.println(fun3Result);
    }

    public static class Obj1{
        private int x = 1;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return x+"";
        }
    }
}
