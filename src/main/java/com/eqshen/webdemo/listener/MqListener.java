package com.eqshen.webdemo.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqListener {

    @Value("${mq.queue.simpleQueue}")
    private String simpleQueue;


    @RabbitListener(queues = {"simple_queue1","simple_queue2"})
    public void simpleQueueConsume(Message msg){
        System.out.println("simpleQueueConsume接收到消息："+ new String(msg.getBody()));
    }


    @RabbitListener(queues = {"cid_direct_eqshen"})
    public void directQueueConsume(Message msg){
        System.out.println("directQueueConsume 1接收到消息："+ new String(msg.getBody()));
    }

    @RabbitListener(queues = {"cid_direct_eqshen"})
    public void directQueueConsume2(Message msg){
        System.out.println("directQueueConsume 2接收到消息："+ new String(msg.getBody()));
    }


    @RabbitListener(queues = {"cid_topic_eqshen"})
    public void topicQueueConsume1(Message msg){
        System.out.println("topicQueueConsume 1接收到消息："+ new String(msg.getBody()));
    }
}
