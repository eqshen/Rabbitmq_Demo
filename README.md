# Rabbitmq_Demo
##1
SpringBoot集成RabbitMQ。包含的普通queue的消费，以及Dirct、Topic模式。
由阿里ons向RabbitMQ迁移，结合自己的理解，对二者进行了总结对比（图1，图2）：https://www.processon.com/view/link/5b46b658e4b00c2f18c96fd4

##2 死信队列
将报错后多次重试仍无法处理的消息置入死信队列。
### 流程：
 producer > Exchange > Queue -处-理-失-败-> DeadLetter Exchange > DeadLetter Queue
 具体流程看图3和图4。
 
 注：使用死信队列时，一定需要设置属性
      ```spring.rabbitmq.listener.simple.default-requeue-rejected=false``` 


##3
best practice，仅供参考
https://www.cloudamqp.com/blog/2017-12-29-part1-rabbitmq-best-practice.html