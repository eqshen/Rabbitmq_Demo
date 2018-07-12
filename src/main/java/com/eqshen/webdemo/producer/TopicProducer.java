package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class TopicProducer {
    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate template;



    public void send(String msg){
        System.out.println("send msg:"+msg);
//        template.convertAndSend(MqConstant.TOPIC_EX,MqConstant.ROUTING_TOPIC_TAG_RQ,msg.getBytes());
        Message msgObj = new Message(msg.getBytes(),new MessageProperties());
        template.send(MqConstant.TOPIC_EX,MqConstant.ROUTING_TOPIC_TAG_RQ,msgObj);
    }
}
