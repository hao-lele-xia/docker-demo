package com.example.dockerdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/4/30 16:40
 */
@RestController
@RequestMapping("/docker-demo")
public class HelloController {

    @GetMapping("hello")
    public Object hello(String hello){
        Map<String,String> map = new HashMap<>();
        map.put("1","docker");
        map.put("name",hello);
        return map;
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(String path) throws Exception {
        File file = new File("D:/12.jpg");

        FileInputStream inputStream = new FileInputStream(file);

        byte[] bytes = new byte[inputStream.available()];

        inputStream.read(bytes, 0, inputStream.available());

        return bytes;
    }
}
