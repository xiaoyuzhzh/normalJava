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
        engine.eval(new FileReader("/scripts/nashorn/executable/test.js"));

        Invocable invocable = (Invocable) engine;
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
