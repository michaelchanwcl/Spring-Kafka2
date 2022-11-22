package com.example;

import com.sample.schema.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {

    @KafkaListener(topics = "${avro.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void read(ConsumerRecord<String, Employee> record) {
        String key = record.key();
        Employee employee = record.value();
        System.out.println("Key=[" + key + "], value=[" + employee.toString() + "]");
    }
}
