package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {

    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate template;



    public void simpleSend(String msg){
        template.convertAndSend(MqConstant.SIMPLE_QUEUE1,msg.getBytes());
        template.convertAndSend(MqConstant.SIMPLE_QUEUE2,msg.getBytes());
    }
}
