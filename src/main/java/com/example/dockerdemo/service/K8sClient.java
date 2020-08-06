package com.example.dockerdemo.service;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/7/14 20:04
 */
public class K8sClient extends DefaultKubernetesClient {

    public K8sClient(){
        super();
    }

    public K8sClient(Config config){
        super(config);
    }

    public K8sClient(String masterUrl){
        supportsApiPath(masterUrl);
    }
}
