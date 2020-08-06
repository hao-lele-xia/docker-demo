package com.example.dockerdemo.listener;

/*import com.alibaba.fastjson.JSONException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;*/
import org.springframework.stereotype.Component;

/**
 * @author chenhao
 * @description <p>
 * created b chenhao 2020/6/12 15:51
 */
@Component
public class KafkaConsumerListener {

   /* private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);

    @KafkaListener(topics = {"${kafka.train.task.result.topic:aits_aopb_task_res_topic}"},
            groupId = "${kafka.consumer.group.id:ai_open_data_be_group_id}")
    public void consumer(ConsumerRecord<String,String> data, Acknowledgment ack){
        LOGGER.info("consume kafka data = {}",data);
        try {
            Float f = 2.0f;
            LOGGER.info("f = {}",Integer.parseInt(f.toString()));
        }catch (JSONException e){
            LOGGER.error("illegal data = {}",data);
        }
        ack.acknowledge();
    }

    @KafkaListener(topics = {"aits_aopb_task_res_topic.DLT"},
            groupId = "${kafka.consumer.group.id:ai_open_data_be_group_id}")
    public void consumerDel(ConsumerRecord<String,String> data, Acknowledgment ack){
        LOGGER.info("consume kafka DLT data = {}",data);
        ack.acknowledge();
    }*/
}
