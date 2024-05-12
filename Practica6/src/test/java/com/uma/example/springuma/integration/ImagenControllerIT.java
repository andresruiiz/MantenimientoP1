package com.uma.example.springuma.integration;

import java.time.Duration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Imagen;

import jakarta.annotation.PostConstruct;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagenControllerIT extends AbstractIntegration {
    
        @LocalServerPort
    private Integer port;

    private WebTestClient client;

    private Imagen imagen;

    // Antes de la inyección de dependencias
    @PostConstruct
    public void init() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port)
        .responseTimeout(Duration.ofMillis(30000)).build();

        imagen = new Imagen();
        imagen.setId(1);
        imagen.setNombre("imagen1");
        imagen.setPaciente(null);
        imagen.setFecha(null);
        imagen.setFile_content(null);
    }

}
