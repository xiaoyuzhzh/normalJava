package org.iceberg.mapstruct;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    public static final boolean unchange = new Boolean(false);
    private String make;
    private int numberOfSeats;
    private String type;


    public static void main(String[] args) throws Exception {


//        final Class<?> jceSecurity = Class.forName("javax.crypto.JceSecurity");
//        final Class<?> cryptoPermissions = Class.forName("javax.crypto.CryptoPermissions");
//        final Class<?> cryptoAllPermission = Class.forName("javax.crypto.CryptoAllPermission");
//
//        final Field isRestrictedField = jceSecurity.getDeclaredField("isRestricted");
//        isRestrictedField.setAccessible(true);
//        final Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(isRestrictedField, isRestrictedField.getModifiers() & ~Modifier.FINAL);
//        isRestrictedField.set(null, false);

        Car car = new Car();
        car.setMake("123");
        System.out.println(car);
        System.out.println(car.unchange);
        Field field = car.getClass().getDeclaredField("unchange");
        field.setAccessible(true);
        Field unchangeModifiers = Field.class.getDeclaredField("modifiers");
        unchangeModifiers.setAccessible(true);
        unchangeModifiers.setInt(field,field.getModifiers() & ~Modifier.FINAL);
        field.set(null,true);
//        changeStaticFinal(car.getClass().getDeclaredField("unchange"),2);
        System.out.println(car.unchange);


    }

    static void changeStaticFinal(Field field, Object newValue) throws Exception {
        field.setAccessible(true); // 如果field为private,则需要使用该方法使其可被访问

        Field modifersField = Field.class.getDeclaredField("modifiers");
        modifersField.setAccessible(true);
        // 把指定的field中的final修饰符去掉
        modifersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue); // 为指定field设置新值
    }
}