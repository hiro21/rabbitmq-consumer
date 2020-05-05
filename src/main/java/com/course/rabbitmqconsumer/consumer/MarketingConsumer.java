package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class MarketingConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MarketingConsumer.class);

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message) {
        try {
            Employee emp = objectMapper.readValue(message, Employee.class);
            log.info("On marketing. Employee is {}", emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
