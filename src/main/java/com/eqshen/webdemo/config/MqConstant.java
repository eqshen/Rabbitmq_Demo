package com.eqshen.webdemo.config;

public class MqConstant {

//    queue
    public static String SIMPLE_QUEUE1 = "simple_queue1";
    public static String SIMPLE_QUEUE2 = "simple_queue2";

    public static String DIRECT_QUEUE_EQ = "cid_direct_eqshen";
    public static String TOPIC_QUEUE_EQ = "cid_topic_eqshen";
    public static String DEAD_LETTER_QUEUE = "dead_letter_queue";
    public static String MAINTIAN_QUEUE = "maintain_queue";


//    exchange
    public static String DIRECT_EX = "t_direct_eqshen";
    public static String TOPIC_EX = "t_topic_eqshen";
    public static String DEAD_LETTER_EX = "t_topic_dead";


//    tag,routing_key
    public static String ROUTING_DIRECT_TAG_EQ = "direct_routing_key_eq";
    public static String ROUTING_TOPIC_TAG_RQ = "topic_routing_key_eq";
    public static String ROUTING_TOPIC_TAG_ALL = "topic_routing_key_*";
    public static String ROUTING_DEAD_TAG_EQ = "dead_routing_key_source";
    public static String ROUTING_DEAD_TAG_MAINTAIN = "dead_routing_key_maintain";

}
