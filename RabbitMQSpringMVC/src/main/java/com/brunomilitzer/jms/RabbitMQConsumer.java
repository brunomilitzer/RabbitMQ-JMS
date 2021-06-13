package com.brunomilitzer.jms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
@EnableRabbit
public class RabbitMQConsumer {

    /*@RabbitListener(queues = "Mobile")
    public void getMessage(Person person) {

        System.out.println(person.getName());
    }*/

    @RabbitListener(queues = "Mobile")
    public void getMessage(byte[] message) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInput in = new ObjectInputStream(bis);
        final Person person = (Person) in.readObject();

        System.out.println(person.getName());
    }

}
