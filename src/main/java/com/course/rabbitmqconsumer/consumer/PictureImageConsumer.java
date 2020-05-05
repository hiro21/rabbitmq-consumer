package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PictureImageConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(PictureImageConsumer.class);

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws IOException {
        var p = objectMapper.readValue(message, Picture.class);
        log.info("On image:{}", p.toString());

    }
}
