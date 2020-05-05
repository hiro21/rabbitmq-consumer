package com.course.rabbitmqconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

//@Service
public class FixRateConsumer {
    private final Logger log = LoggerFactory.getLogger(FixRateConsumer.class);

    @RabbitListener(queues = "course.fixedrate", concurrency = "3")
    public void listen(String message) {
        log.info("Consuming {} on thread {}", message, Thread.currentThread().getName());

        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
