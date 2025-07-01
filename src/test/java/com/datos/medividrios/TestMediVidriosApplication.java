package com.datos.medividrios;

import org.springframework.boot.SpringApplication;

public class TestMediVidriosApplication {

    public static void main(String[] args) {
        SpringApplication.from(MediVidriosApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
