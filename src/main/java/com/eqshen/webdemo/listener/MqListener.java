package com.eqshen.webdemo.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqListener {



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
    
    @RabbitListener(queues = {"${mq.queue.topicQueue}"})
    public void topicQueueConsume1(Message msg) throws InterruptedException {
        System.out.println(new Date() +"topicQueueConsume 1接收到消息："+ new String(msg.getBody()));
//        Thread.sleep(10000);
//        throw  new RuntimeException("topicQueueConsume1   exception");
    }


//    @RabbitListener(queues = {"cid_topic_eqshen"})
    public void topicQueueConsume2(Message msg){
        System.out.println(new Date()+ "topicQueueConsume 2接收到消息："+ new String(msg.getBody()));
        throw  new RuntimeException("topicQueueConsume2   exception");
    }
}
