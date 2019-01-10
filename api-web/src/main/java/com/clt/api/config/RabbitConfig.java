package com.clt.api.config;

import com.clt.api.listener.ReturnCallBackListener;
import com.clt.api.mq.RewardMq;
import com.clt.api.sender.RabbitAckSender;
import com.clt.api.utils.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName : RabbitConfig
 * @Author : zhangquansong
 * @Date : 2019/1/9 0009 下午 5:13
 * @Description :rabbit配置
 **/
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbit.queue.name}")
    private String messageQueue;

    @Value("${spring.rabbit.topic.exchange}")
    private String topicExchange;

    @Value("${spring.rabbit.topic.routingkey}")
    private String routeKey;


    @Bean
    public RabbitAckSender rabbitAckSender() {
        RabbitAckSender rabbitAckSender = new RabbitAckSender();
//        rabbitAckSender.sendReward(new RewardMq(Long.valueOf(1), "15007257865", "1", "cs"));
        return rabbitAckSender;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReturnCallback(new ReturnCallBackListener());
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue getMessageQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(messageQueue, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public TopicExchange exchangeFanout(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        TopicExchange topicExchange = new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE, true, false, arguments);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    @Bean
    public Queue getMessageSMSQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(RabbitMqConstants.CLT_QUEUE_SMS, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue getMessageRewardQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(RabbitMqConstants.CLT_QUEUE_REWARD, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        TopicExchange exchange = new TopicExchange(routeKey, true, false, arguments);
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding bindingExchange(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(getMessageQueue(rabbitAdmin)).to(exchange(rabbitAdmin)).with(routeKey);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    /**
     * 配置广播路由器
     *
     * @return
     * @date 2018年5月4日 下午3:38:08
     * @author wangj@boruijinfu.com
     */
    @Bean
    public Binding bindingFanoutSMSExchange(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(getMessageSMSQueue(rabbitAdmin)).to(exchangeFanout(rabbitAdmin)).with(RabbitMqConstants.CLT_QUEUE_ROUTINGKEY);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    /**
     * 配置广播路由器
     *
     * @return
     * @date 2018年5月4日 下午3:38:08
     * @author wangj@boruijinfu.com
     */
    @Bean
    public Binding bindingFanoutRewardExchange(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(getMessageRewardQueue(rabbitAdmin)).to(exchangeFanout(rabbitAdmin)).with(RabbitMqConstants.CLT_QUEUE_ROUTINGKEY);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public DirectExchange defaultExchange(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        DirectExchange directExchange = new DirectExchange(RabbitMqConstants.DEFAULT_EXCHANGE, true, false, arguments);
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    @Bean
    public Queue repeatTradeQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(RabbitMqConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding repeatTradeBinding(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(repeatTradeQueue(rabbitAdmin)).to(defaultExchange(rabbitAdmin)).with(RabbitMqConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Queue deadLetterQueue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", RabbitMqConstants.DEFAULT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", RabbitMqConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(RabbitMqConstants.DEFAULT_DEAD_LETTER_QUEUE_NAME, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding deadLetterBinding(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(deadLetterQueue(rabbitAdmin)).to(defaultExchange(rabbitAdmin)).with(RabbitMqConstants.DEFAULT_DEAD_LETTER_QUEUE_NAME);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Queue queue(RabbitAdmin rabbitAdmin) {
        Map<String, Object> arguments = new HashMap<>(1);
        arguments.put("x-ha-policy", RabbitMqConstants.X_HA_POLICY_ALL);
        Queue queue = new Queue(RabbitMqConstants.NORMAL_QUEUE, true, false, false, arguments);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding binding(RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue(rabbitAdmin)).to(defaultExchange(rabbitAdmin)).with(RabbitMqConstants.NORMAL_QUEUE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}

