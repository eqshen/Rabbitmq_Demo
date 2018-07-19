package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class DeadLetterProducer {
    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate template;


    /**
     * 将消息发送到业务队列
     * @param msg
     */
    public void send(String msg){
        System.out.println("send msg:"+msg);
        MessageProperties mp = new MessageProperties();
        mp.setMessageId(UUID.randomUUID().toString());
        Message msgObj = new Message(msg.getBytes(),mp);
        template.send(MqConstant.TOPIC_EX,MqConstant.ROUTING_DEAD_TAG_MAINTAIN,msgObj);
    }

}
