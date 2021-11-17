package com.picpay.carteiradigital.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    public static final String QUEUE = "outro-banco";
    public static final String EXCHANGE_NAME = "outrobanco";
    public static final String ROUTING_KEY = "";

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
    	return new Jackson2JsonMessageConverter();
    }
}