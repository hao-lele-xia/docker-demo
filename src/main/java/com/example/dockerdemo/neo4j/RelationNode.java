package com.example.dockerdemo.neo4j;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/6 10:42
 */
@RelationshipEntity(type = "RelationEdge")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
public class RelationNode {
    private @Id
    @GeneratedValue
    Long id;
    private @StartNode
    ParentNode parentNode;
    // 关系名
    private String name;
    private @EndNode
    SonNode sonNode;

    RelationNode(ParentNode parentNode, String name, SonNode sonNode){
        this.parentNode = parentNode;
        this.name = name;
        this.sonNode = sonNode;

    }
}