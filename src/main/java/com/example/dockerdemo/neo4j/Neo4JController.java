package com.example.dockerdemo.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/6 10:45
 */
@RestController
@Slf4j
@RequestMapping("/neo4j")
public class Neo4JController {
    @Autowired
    ParentReporitory parentReporitory;

    @GetMapping(value = "/test1")
    public void test1(){
        SonNode sonNode1 = new SonNode("孩子柳依依");
        SonNode sonNode2 = new SonNode("孩子柳风");

        ParentNode parentNode = new ParentNode("青年:柳大叔");

        parentNode.addRelation(sonNode1,"女儿");
        parentNode.addRelation(sonNode2,"儿子");

        parentReporitory.save(parentNode);
    }

    @GetMapping(value = "/test2")
    public void test2(){
        Iterable<ParentNode> parentNodes = parentReporitory.findAll();
        Iterator<ParentNode> iterator = parentNodes.iterator();
        while (iterator.hasNext()){
            ParentNode parentNode = iterator.next();
            Set<RelationNode> relationNodeSet = parentNode.getSets();
            for (RelationNode relationNode : relationNodeSet){
                log.info("id:"+parentNode.getId()+"    name:"+parentNode.getName() +"     关系："+relationNode.getName() +"子节点："+relationNode.getSonNode().getName());
            }

        }
    }
}
