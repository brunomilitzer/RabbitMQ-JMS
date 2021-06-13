package com.brunomilitzer.jms;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -8459881636047352386L;

    public Person(final Long id, final String name) {

        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
    }

    public String getName() {

        return this.name;
    }

    public void setName(final String name) {

        this.name = name;
    }

}
