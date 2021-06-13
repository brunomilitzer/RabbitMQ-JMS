package com.brunomilitzer.jms;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitMQConsumer {

    /*@RabbitListener(queues = "Mobile")
    public void getMessage(final Person person) {

        System.out.println(person.getName());
    }*/

    @RabbitListener(queues = "TV")
    public void getMessage(final byte[] message) throws IOException, ClassNotFoundException {

        final ByteArrayInputStream bis = new ByteArrayInputStream(message);
        final ObjectInput in = new ObjectInputStream(bis);
        final Person person = (Person) in.readObject();
        in.close();
        bis.close();

        System.out.println(person.getName());
    }

}
