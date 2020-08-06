package com.example.dockerdemo.neo4j;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/6 10:41
 */
@Getter
@NodeEntity("ParentNode")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@ToString
public class ParentNode {
    private @Id
    @GeneratedValue
    Long id;
    private @Property(name = "name") String name;
    private @Relationship(type = "RelationEdge")
    Set<RelationNode> sets = new HashSet<>();

    public ParentNode(String name){
        this.name = name;
    }

    public void addRelation(SonNode sonNode, String name){
        RelationNode relationNode = new RelationNode(this,name,sonNode);
        sets.add(relationNode);
        sonNode.getSets().add(relationNode);
    }
}
