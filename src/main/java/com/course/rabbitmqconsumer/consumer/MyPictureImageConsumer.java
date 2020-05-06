package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyPictureImageConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MyPictureImageConsumer.class);

    @RabbitListener(queues = "q.mypicture.image")
    public void listen(String message) throws IOException {
        var p = objectMapper.readValue(message, Picture.class);

        if (p.getSize() > 9000) {
            throw new IllegalArgumentException("picture size too large:" + p.getSize());
        }
        log.info("On image:{}", p.toString());

    }
}
