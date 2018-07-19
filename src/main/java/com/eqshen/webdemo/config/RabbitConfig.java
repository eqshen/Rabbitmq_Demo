package com.eqshen.webdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {


    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;


    @Bean
    public Queue queue(){
        return new Queue(MqConstant.SIMPLE_QUEUE1);
    }

    @Bean
    public Queue queue2(){
        return new Queue(MqConstant.SIMPLE_QUEUE2);
    }


    @Bean
    public Queue queueDirect(){
        return new Queue(MqConstant.DIRECT_QUEUE_EQ,true);
    }

    @Bean
    public Queue queueTopic(){
        return new Queue(MqConstant.TOPIC_QUEUE_EQ,true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MqConstant.DIRECT_EX,true,false);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MqConstant.TOPIC_EX,true,false);
    }

    @Bean
    public Binding directExchangeBinding(){
        return BindingBuilder.bind(queueDirect())
                .to(directExchange())
                .with(MqConstant.ROUTING_DIRECT_TAG_EQ);
    }

    @Bean
    public Binding topicExchangeBinding(){
        return BindingBuilder.bind(queueTopic())
                .to(topicExchange())
                .with(MqConstant.ROUTING_TOPIC_TAG_ALL);
    }




    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    //支持回调的template
    @Bean("rabbitTemplatenew")
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    //===================死信队列相关声明===========================

    /**
     * 死信交换机，与普通的交换机一样，用于路由分发死信
     * @return
     */
    @Bean("dedaLetterExchange")
    public TopicExchange deadLetterExchange(){
        return new TopicExchange(MqConstant.DEAD_LETTER_EX,true,false);
    }



    /**
     * 将普通的队列升级为支持死信
     * 死信生产者
     * @return
     */
    @Bean("maintainQueue")
    public Queue maintainQueue(){
        Map<String, Object> args = new HashMap<>(2);
        //如果队列中存在死信，将被发送到下面的exchange-routingkey
        args.put("x-dead-letter-exchange",MqConstant.DEAD_LETTER_EX);
        args.put("x-dead-letter-routing-key",MqConstant.ROUTING_DEAD_TAG_EQ);
        return new Queue(MqConstant.MAINTIAN_QUEUE,true,false,false,args);
    }

    /**
     * 死信队列
     * 死信都将被发送到该队列
     * @return
     */
    @Bean("deadQueue")
    public Queue deadLetterQueue(){
        return new Queue(MqConstant.DEAD_LETTER_QUEUE,true,false,false);
    }


    @Bean
    public Binding maintainBinding() {
        return BindingBuilder.bind(maintainQueue()).to(topicExchange())
                .with(MqConstant.ROUTING_DEAD_TAG_MAINTAIN);
    }


    @Bean
    public Binding deadLetterBindding(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange())
                .with(MqConstant.ROUTING_DEAD_TAG_EQ);
    }

}
