package com.brunomilitzer.jms;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    RabbitTemplate rabbitTemplate;

    @Autowired
    public TestController(final RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    /*@GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") final String name) {

        final Person person = new Person(1L, name);

        this.rabbitTemplate.convertAndSend("Mobile", person);
        this.rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person);
        this.rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
        this.rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);

        return "Success";
    }*/

    @GetMapping("/test/{name}")
    public String testApi(@PathVariable("name") final String name) throws IOException {

        final Person person = new Person(1L, name);

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(person);
        out.flush();
        out.close();

        final byte[] byteMessage = bos.toByteArray();
        bos.close();

        final Message message = MessageBuilder.withBody(byteMessage).setHeader("item1", "mobile").setHeader("item2", "television").build();

        this.rabbitTemplate.send("Headers-Exchange", "", message);

        return "Success";
    }

}
