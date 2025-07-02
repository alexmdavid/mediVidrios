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

        Vidrio vidrio = new Vidrio(23, 34);
        VidrioService vidrioService = new VidrioService();
        double []medida = vidrioService.cubicar(vidrio.getAncho_cm(), vidrio.getAlto_cm());
        System.out.println("medida: " + Arrays.toString(medida));
        double resul = medida[0]*medida[1];
        System.out.println("resul: " + resul);
    }

}
