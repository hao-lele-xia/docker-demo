package com.example.dockerdemo.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/8/6 10:43
 */
public interface ParentReporitory extends Neo4jRepository<ParentNode,Long> {
}
