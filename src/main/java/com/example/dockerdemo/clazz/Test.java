package com.example.dockerdemo.clazz;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/7 15:33
 */
public class Test {

    public static void testField(){
        Child child = new Child();
        child.setId(1);
        child.setName("son");
        Child child2 = new Child();
        child2.setId(2);
        child2.setName("doutor");
        List<Child> children = new ArrayList<>();
        children.add(child);
        children.add(child2);
        Parent parent = new Parent();
        parent.setId(100);
        parent.setName("p");
        parent.setChildren(children);
        Other wife = new Other();
        wife.setId(0);
        wife.setName("wife");
        parent.setWife(wife);
        Other obj = new Other();
        wife.setId(-1);
        wife.setName("obj");
        parent.setObj(obj);
        parent.trans();
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test2(){
        Integer i = 1;
        try {
            Class clz = Class.forName(i.getClass().getName());
            Field[] declaredFields = clz.getDeclaredFields();
            System.out.println(declaredFields.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
