package com.example.dockerdemo.controller;

import com.example.dockerdemo.util.CompressUtil;
import com.example.dockerdemo.util.ShellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/6/6 19:48
 */
@RestController
@RequestMapping("test")
public class CompressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompressController.class);

    @GetMapping("compress")
    public Map<String,Long> testCompress(String source,String dest1,String dest2,String suffix) throws Exception {
        LOGGER.error("source = {},dest1 = {},dest2 = {},suffix = {}",source,dest1,dest2,suffix);
        Map<String,Long> map = new HashMap<>();
        Long t1 = System.currentTimeMillis();
        CompressUtil.compress(source,dest1,suffix);
        Long t2 = System.currentTimeMillis();
        map.put("util compress",t2 - t1);
        Long t3 = System.currentTimeMillis();
        ShellUtil.compress(source,dest2,suffix);
        Long t4 = System.currentTimeMillis();
        map.put("shell compress",t4 - t3);
        return map;
    }

    @GetMapping("uncompress")
    public Map<String,Long> testUnCompress(String source,String dest1,String dest2,String suffix) throws Exception {
        LOGGER.error("source = {},dest1 = {},dest2 = {},suffix = {}",source,dest1,dest2,suffix);
        Map<String,Long> map = new HashMap<>();
        Long t1 = System.currentTimeMillis();
        CompressUtil.unCompress(source,dest1,suffix);
        Long t2 = System.currentTimeMillis();
        map.put("util uncompress",t2 - t1);
        Long t3 = System.currentTimeMillis();
        ShellUtil.unCompress(source,dest2,suffix);
        Long t4 = System.currentTimeMillis();
        map.put("shell uncompress",t4 - t3);
        return map;
    }

    @GetMapping("delete")
    public Map<String,Long> testDelete(String source1,String source2) throws Exception {
        LOGGER.error("source1 = {},source2 = {}",source1,source2);
        Map<String,Long> map = new HashMap<>();
        Long t1 = System.currentTimeMillis();
        CompressUtil.deleteFileOrFolder(source1);
        Long t2 = System.currentTimeMillis();
        map.put("util delete",t2 - t1);
        Long t3 = System.currentTimeMillis();
        ShellUtil.deleteFile(source2);
        Long t4 = System.currentTimeMillis();
        map.put("shell uncompress",t4 - t3);
        return map;
    }

}
