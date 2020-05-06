package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyPictureImageConsumer {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(MyPictureImageConsumer.class);

    @RabbitListener(queues = "q.mypicture.image")
    //public void listen(String message) throws IOException {
    public void listen(Message message, Channel channel) throws IOException {
        //var p = objectMapper.readValue(message, Picture.class);
        var p = objectMapper.readValue(message.getBody(), Picture.class);

        if (p.getSize() > 9000) {
            // 例外スローではなく手動で実施する方法
            //throw new AmqpRejectAndDontRequeueException("picture size too large:" + p.getSize());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);

        }
        log.info("On image:{}", p.toString());

        // RabbitMQにプロセスが完了したことを伝える必要がある。
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }
}
