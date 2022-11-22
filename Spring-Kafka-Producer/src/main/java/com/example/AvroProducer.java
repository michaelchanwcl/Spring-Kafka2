package com.example;

import com.sample.schema.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * The code is from
 * https://github.com/vishaluplanchwar/KafkaTutorials
 */

@Service
public class AvroProducer {

    @Value("${avro.topic.name}")
    String topicName;

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    public void send(Employee stockHistory) {
        ListenableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, String.valueOf(stockHistory.getId()), stockHistory);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Employee>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message failed to produce");
            }

            @Override
            public void onSuccess(SendResult<String, Employee> result) {
                System.out.println("Avro message successfully produced");
            }
        });

    }
}
