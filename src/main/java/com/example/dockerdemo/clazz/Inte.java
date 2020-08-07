package com.example.dockerdemo.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/7 15:39
 */
public interface Inte {

    default void setName(String name){}

    default String getName(){
        return null;
    }

    default void trans(){
        try {
            Class clazz = Class.forName(this.getClass().getName());
            Field[] declaredFields = clazz.getDeclaredFields();
            System.out.println(this.getName());
            for (Field field : declaredFields) {
                Class<?>[] interfaces = field.getType().getInterfaces();
                field.setAccessible(true);
                Object o = field.get(this);
                if(Arrays.asList(interfaces).contains(Inte.class)){
                    Inte other = (Inte) o;
                    other.trans();
                }
                if(field.getType() == List.class){
                    List list = (List) o;
                    list.forEach(t -> {
                        if(t instanceof Inte){
                            ((Inte) t).trans();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
