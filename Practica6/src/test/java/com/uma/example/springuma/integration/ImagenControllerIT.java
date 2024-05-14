package com.uma.example.springuma.integration;

import java.io.File;
import java.time.Duration;
import java.util.Calendar;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;

import org.springframework.http.MediaType;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ImagenControllerWebTestClientIT {

    @LocalServerPort
    private Integer port;

    private WebTestClient client;

    private Imagen imagen;

    private Paciente paciente;

    private Medico medico;

    // After dependency injection
    @PostConstruct
    public void init() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:"+port)
        .responseTimeout(Duration.ofMillis(30000)).build();

        medico = new Medico();
        medico.setDni("123ABC");
        medico.setNombre("juan");
        medico.setEspecialidad("Mamografias");
        medico.setId(1);

        paciente = new Paciente();
        paciente.setDni("456DEF");
        paciente.setNombre("paco");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        paciente.setMedico(medico);

        imagen = new Imagen();
        imagen.setId(1);
        imagen.setNombre("Mamografía");
        imagen.setFecha(Calendar.getInstance());
        imagen.setPaciente(paciente);

    }

    @Test
    @DisplayName("Crear imagen")
    public void crearPersonaPost_seObtieneConGet() {

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));

        //Crea una imagen
        FluxExchangeResult<String> responseBody = client.post()
            .uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        String result = responseBody.getResponseBody().blockFirst();
        
        assertEquals("{\"response\" : \"file uploaded successfully : healthy.png \"}", result);
    }
}