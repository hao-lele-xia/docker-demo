package com.example.dockerdemo.controller;

import com.example.dockerdemo.service.K8sClient;
import io.fabric8.kubernetes.api.model.DoneablePod;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.dsl.PodResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/7/14 20:02
 */
@RestController
@RequestMapping("k8s")
@Slf4j
public class K8sController {

    private final K8sClient k8sClient;

    @Autowired
    public K8sController(K8sClient k8sClient){
        this.k8sClient = k8sClient;
    }

    @GetMapping("pods")
    public Object getPods(){
        return k8sClient.pods().list();
    }

    @GetMapping("nodes")
    public Object getNodes(){
        return k8sClient.nodes().list();
    }

    @GetMapping("ns")
    public Object getNameSpaces(){
        return k8sClient.namespaces().list();
    }

    @GetMapping("apps")
    public Object getNum(){
        return k8sClient.apps().statefulSets()
                .inNamespace("default")
                .withName("viid-vehicle").get()
                .getSpec()
                .getReplicas();
    }

    @GetMapping("delete")
    public boolean deletePod(){
        PodResource<Pod, DoneablePod> pod = k8sClient.pods()
                .inNamespace("default")
                .withName("viid-cms-web-0");
        log.info("pod = {}",pod);
        return pod
                .delete();
    }
}
