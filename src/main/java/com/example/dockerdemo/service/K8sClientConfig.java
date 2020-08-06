package com.example.dockerdemo.service;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/7/14 20:04
 */
@Configuration
public class K8sClientConfig {

    @Bean
    public K8sClient k8sClient(){
        Config config = new ConfigBuilder()
//                .withCaCertFile("/home/ssl/ca.pem")
//                .withClientCertFile("/home/ssl/admin.pem")
//                .withClientKeyFile("/home/ssl/admin-key.pem")
                .build();
        return new K8sClient(config);
    }
}
