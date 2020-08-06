package com.example.dockerdemo;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/5/11 9:01
 */
public class ImagYaShuoTests {
    public static void main(String[] args) throws IOException {
        String path = "D:/tmp/Desert.jpg";
        Thumbnails.of(path)
                .scale(0.2f)
                .outputQuality(0.5f)
                .toFile("D:/tmp/Desert-zip.jpg");
    }
}
