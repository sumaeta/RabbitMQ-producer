package com.code.producer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitConfiguration {

    @Autowired
    private ConnectionFactory factory;

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(factory);
        simpleRabbitListenerContainerFactory.setMessageConverter(converter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public RabbitTemplate template(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter converter (){
        final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .dateFormat(new StdDateFormat())
                .build();
        return new Jackson2JsonMessageConverter(mapper);
    }
}
