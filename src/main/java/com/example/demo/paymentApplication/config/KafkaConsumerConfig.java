package com.example.demo.paymentApplication.config;


import com.example.demo.paymentApplication.model.Payment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.hibernate.query.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
    @EnableKafka
    public class KafkaConsumerConfig {

        @Bean
        public ConsumerFactory<String, Payment> consumerFactory() {
            JsonDeserializer<Payment> deserializer = new JsonDeserializer<>(Payment.class);
            deserializer.addTrustedPackages("*");

            Map<String, Object> config = new HashMap<>();
            config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            config.put(ConsumerConfig.GROUP_ID_CONFIG, "payment-group");

            return new DefaultKafkaConsumerFactory<>(
                    config,
                    new StringDeserializer(),
                    deserializer
            );
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<String, Payment> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }
    }

