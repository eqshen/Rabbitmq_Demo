package com.eqshen.webdemo.producer;

import com.eqshen.webdemo.config.MqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class CallBackProducer implements RabbitTemplate.ConfirmCallback{

    @Resource(name = "rabbitTemplatenew")
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(MqConstant.DIRECT_EX,MqConstant.ROUTING_DIRECT_TAG_EQ,msg.getBytes(),correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(correlationData.getId() + "   "+ack + "   "+ cause);
    }
}
