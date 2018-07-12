package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DirectProducer {
    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate template;



    public void directSend(String msg){
        System.out.println("send msg:"+msg);
        template.convertAndSend(MqConstant.DIRECT_EX,MqConstant.ROUTING_DIRECT_TAG_EQ,msg.getBytes());
    }
}
