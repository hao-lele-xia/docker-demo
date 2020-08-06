package com.example.dockerdemo.neo4j;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/6 10:42
 */
@Getter
@NodeEntity("SonNode")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class SonNode {
    private @Id
    @GeneratedValue
    Long id;
    private @Property(name = "name") String name;
    private @Relationship(type = "RelationEdge", direction = "INCOMING")
    Set<RelationNode> sets = new HashSet<>();
    public SonNode(String name){
        this.name = name;
    }
}
