package com.example.dockerdemo.clazz;

import lombok.Data;

import java.util.List;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/7 15:29
 */
@Data
public class Parent implements Inte{

    private Integer id;

    private String name;

    private List<Child> children;

    private Other wife;

    private Object obj;
}
