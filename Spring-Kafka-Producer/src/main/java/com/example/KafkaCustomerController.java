package com.example;

import com.sample.schema.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaCustomerController {
    @Autowired
    public AvroProducer avroProducer;

    @PostMapping("/employees/{id}/{firstName}/{lastName}")
    public String producerAvroMessage(@PathVariable int id, @PathVariable String firstName,
                                      @PathVariable String lastName) {
        Employee employee = Employee.newBuilder().setId(id).build();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        avroProducer.send(employee  );
        return "Sent employee details to consumer";
    }
}
