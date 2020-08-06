/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.example.dockerdemo.config;

/*import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.listener.*;
//import org.springframework.retry.RecoveryCallback;
//import org.springframework.retry.RetryContext;
//import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * kafka 配置文件类
 *
 * @author  tanjiquan
 * @date    2018/9/21 17:52
 * @since   1.0
 */
@Configuration
//@EnableKafka
public class KafkaConsumerConfig {

    /** kafka 地址 */
    @Value("${kafka.consumer.bootstrap.servers}")
    private String brokerAddress;

    /** 消费组ID */
    @Value("${kafka.consumer.group.id}")
    private String groupId;

    /** 是否自动提交 */
    @Value("${kafka.consumer.auto.commit.enable}")
    private String autoCommit;

    /**
     * earliest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
     * latest 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
     * none topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
     */
    @Value("${kafka.consumer.auto.offset.reset}")
    private String offsetReset;

    /** 每次拉取最大条数 **/
    @Value("${kafka.consumer.max.pol.records}")
    private int maxPollRecord;

    /** consumer 连接超时时间 */
    @Value("${kafka.consumer.session.timeout}")
    private Integer sessionTimeout;

    /** consumer 请求超时时间 */
    @Value("${kafka.consumer.request.timeout}")
    private Integer requestTimeout;

    /** consumer poll 超时时间 */
    @Value("${kafka.consumer.poll.timeout}")
    private Integer pollTimeout;

    /** 消费组线程 */
    @Value("${kafka.consumer.concurrency}")
    private int kafkaConcurrency;

    /**
     * hash map初始化
     */
    private static final Integer HASH_MAP_INIT = 9;

//    @Autowired
//    private DeadLetterPublishingRecoverer recoverer;
//
//    @Bean
//    public DeadLetterPublishingRecoverer recoverer(KafkaTemplate<?, ?> stringTemplate,
//                                                   KafkaTemplate<?, ?> bytesTemplate) {
//
//        Map<Class<?>, KafkaTemplate<?, ?>> templates = new LinkedHashMap<>();
//        templates.put(String.class, stringTemplate);
//        templates.put(byte[].class, bytesTemplate);
//        return new DeadLetterPublishingRecoverer(templates);
//    }
//
//    /**
//     * KafkaListenerContainerFactory
//     * @return KafkaListenerContainerFactory
//     */
//    @Bean("kafkaListenerContainerFactory")
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(false);
//        factory.setConcurrency(kafkaConcurrency);
//        // listener负责ack，但是背后也是批量上去
//         factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
//        // 单节点最大 kafka 接受线程数
//        factory.getContainerProperties().setPollTimeout(pollTimeout);
//        factory.setStatefulRetry(true);
//        factory.setRetryTemplate(new RetryTemplate());
//        factory.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(0,3)));
//        factory.setRecoveryCallback(new RecoveryCallback<ConsumerRecord<?, ?>>() {
//            @Override
//            public ConsumerRecord<?, ?> recover(RetryContext retryContext) throws Exception {
//                recoverer.accept((ConsumerRecord<?, ?>) retryContext.getAttribute("record"),
//                        (Exception) retryContext.getLastThrowable());
//                return null;
//            }
//        });
//        return factory;
//    }
//
//    /**
//     * consumerFactory
//     * @return consumerFactory
//     */
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> propsMap = new HashMap<>(HASH_MAP_INIT);
//        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
//        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
//        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);
//        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecord);
//        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
//        propsMap.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
//        // 消息中 key
//        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        return new DefaultKafkaConsumerFactory<>(propsMap);
//    }

}