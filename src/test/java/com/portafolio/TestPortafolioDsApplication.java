package com.portafolio;

import org.springframework.boot.SpringApplication;

public class TestPortafolioDsApplication {

    public static void main(String[] args) {
        SpringApplication.from(PortafolioDsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
