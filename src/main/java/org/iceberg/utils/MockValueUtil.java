package org.iceberg.utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoy on 2016/7/19.
 */
public class MockValueUtil {

    public static int MOCK_LIST_LENGTH = 5;

    public static boolean DEBUG = false;



    /**
     * 根据传入的type类型，创建实例并返回值
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mockValue(Class<T> clazz){
        if(clazz.isInterface()){
            return null;
        }

        try {
            T t = getValueIfWrapperClass(clazz);
            if(t!=null) return t;
            t = clazz.newInstance();
            setMockValue(t);
            return t;
        } catch (InstantiationException e) {
            if(DEBUG) e.printStackTrace();
        } catch (IllegalAccessException e) {
            if(DEBUG) e.printStackTrace();
        }
        return null;
    }

    /**
     * 给创建好的实例属性赋值，传入null不处理
     * 实例内属性，如果赋值了子类，则按照子类赋值，如果没有，则按照父类赋值，接口不赋值
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
                mockFieldValue(t, f);
            } catch (Exception e) {
                if(DEBUG) e.printStackTrace();
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
    private static <T> void mockFieldValue(T t, Field f) throws IllegalAccessException {

        //基本类型不做处理
        if(f.getType().isPrimitive()){
            return ;
        }


        /***************       基本包装类型处理开始          **************/
        if(getValueIfWrapperClass(f.getType())!=null){
            f.set(t,getValueIfWrapperClass(f.getType()));
            return;
        }
        /***************       基本包装类型处理结束          **************/




        /***************       指定类型处理开始          **************/
        if(f.getType().equals(List.class)){
            Type type = f.getGenericType();
            if(type instanceof ParameterizedType){
                ParameterizedType pt = (ParameterizedType)type;
                Type types[] = pt.getActualTypeArguments();
                if(types.length == 0){
                    f.set(t,new LinkedList<>());
                }else{
                    Type tp = types[0];
                    List l = new LinkedList<>();
                    Class c = (Class)tp;
                    if(c.isInterface()){
                        System.out.println(c.getName()+" is a interface,can't be instantiated");
                        f.set(t,new LinkedList<>());
                    }else{
                        for(int i = 0 ; i<MOCK_LIST_LENGTH;i++){
                            l.add(mockValue(c));
                        }
                        f.set(t,l);
                    }
                }
            }
            return;
        }



        /***************       指定类型处理结束          **************/


        if(f.get(t)!=null){//给已有对象赋值
            setMockValue(f.get(t));
        }else{
            f.set(t,mockValue(f.getType()));
        }
        return ;
    }


    public static <T> T getValueIfWrapperClass(Class<T> clazz){
        if(clazz == null){
            return null;
        }
        if(clazz.equals(String.class)){
            return (T)"1";
        }
        if(clazz.equals(Long.class)){
            return (T)Long.valueOf(1);
        }
        if(clazz.equals(Integer.class)){
            return (T) Integer.valueOf(1);
        }
        if(clazz.equals(Boolean.class)){
            return (T)Boolean.valueOf(true);
        }
        if(clazz.equals(BigDecimal.class)){
            return (T)BigDecimal.ONE;
        }
        if(clazz.equals(Date.class)){
            return (T)new Date();
        }
        if(clazz.equals(Double.class)){
            return (T)Double.valueOf(1.00);
        }
        if(clazz.isEnum()){
            if(clazz.getEnumConstants().length!=0){
                return clazz.getEnumConstants()[0];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MockValueUtil.DEBUG = true;
        MockValueUtil.MOCK_LIST_LENGTH = 2;
        System.out.println(JSON.toJSONString(mockValue(Result.class),true));
    }

    static class Result{
        private List<Serializable> a;

        private Map<Object,List> b;

        public List<Serializable> getA() {
            return a;
        }

        public void setA(List<Serializable> a) {
            this.a = a;
        }

        public Map<Object, List> getB() {
            return b;
        }

        public void setB(Map<Object, List> b) {
            this.b = b;
        }
    }

}


