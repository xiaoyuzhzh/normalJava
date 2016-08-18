package org.iceberg.utils;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaoy on 2016/7/19.
 */
public class MockValueUtil {

    /**
     * 根据传入的type类型，创建实例并返回值
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mockValue(Class<T> clazz){
        try {
            T t = clazz.newInstance();
            setMockValue(t);
            return t;
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    /**
     * 给创建好的实例属性赋值，传入null不处理
     * @param t
     * @param <T>
     */
    public static <T> void setMockValue(T t) {
        if(t == null) return;
        setMockValue(t, t.getClass());
    }

    /**
     * 给创建好的实例属性赋值
     * @param t
     * @param clazz
     * @param <T>
     */
    private static <T> void setMockValue(T t ,Class clazz){
        setMockValue(t, clazz.getDeclaredFields());
        if(clazz.getSuperclass()!=null){
            setMockValue(t ,clazz.getSuperclass());
        }
    }

    /**
     * 给实例字段赋值
     * @param t
     * @param fields
     * @param <T>
     */
    private static <T> void setMockValue(T t, Field[] fields) {
        for(Field f : fields){
            f.setAccessible(true);
            try {
                if (mockFieldValue(t, f)) continue;
            } catch (Exception e) {
            }
        }
    }

    /**
     * 给具体的字段赋值
     * @param t
     * @param f
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private static <T> boolean mockFieldValue(T t, Field f) throws IllegalAccessException {
        //基本类型不做处理
        if(f.getType().isPrimitive()){
            return true;
        }

        /***************       基本包装类型处理开始          **************/
        if(f.getType().equals(String.class)){
            f.set(t,String.valueOf("1"));
            return true;
        }
        if(f.getType().equals(Long.class)){
            f.set(t,Long.valueOf(1l));
            return true;
        }
        if(f.getType().equals(Integer.class)){
            f.set(t,Integer.valueOf(1));
            return true;
        }
        if(f.getType().equals(Boolean.class)){
            f.set(t,Boolean.valueOf(true));
            return true;
        }
        if(f.getType().equals(BigDecimal.class)){
            f.set(t,BigDecimal.valueOf(11));
            return true;
        }
        if(f.getType().equals(Date.class)){
            f.set(t,new Date());
            return true;
        }
        /***************       基本包装类型处理结束          **************/



        f.set(t,mockValue(f.getType()));
        return false;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(mockValue(Result.class),true));
    }

}


class Result{
    private String code;
    private String description;
    private Object Result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }
}
