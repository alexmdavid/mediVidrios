package com.datos.medividrios;

import com.datos.medividrios.model.Vidrio;
import com.datos.medividrios.service.VidrioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MediVidriosApplication {

    public static void main(String[] args) {

        SpringApplication.run(MediVidriosApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "Medividrios123";
        String hash = encoder.encode(rawPassword);
        System.out.println(hash);


    }

}
