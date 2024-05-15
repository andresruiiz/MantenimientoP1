package com.uma.example.springuma.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InformeControllerIT {
    
    @LocalServerPort
    private Integer port;

    private WebTestClient client;

    private Paciente paciente;

    private Medico medico;

    private Imagen imagen;

    private Informe informe;

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
        paciente.setNombre("carmen");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        paciente.setMedico(medico);

        imagen = new Imagen();
        imagen.setId(1);

        informe = new Informe();
        informe.setContenido("La paciente tiene cáncer de mama");
        informe.setPrediccion("Cancer");
        informe.setId(1);
        informe.setImagen(imagen);
    }

    @Test
    @DisplayName("Crear un informe y conseguirlo correctamente")
    public void crearInformePost_seConsigueConGet() {

        //Crea el medico
        client.post().uri("/medico")
            .body(Mono.just(medico), Medico.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        //Crea el paciente
        client.post().uri("/paciente")
            .body(Mono.just(paciente), Paciente.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        File uploadFile = new File("./src/test/resources/no_healthty.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Crea un informe
        client.post().uri("/informe")
            .body(Mono.just(informe), Informe.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();
        
        //Obtiene el informe con ID 1
        FluxExchangeResult<Informe> result = client.get().uri("/informe/1")
            .exchange()
            .expectStatus().isOk().returnResult(Informe.class);

        Informe informeObtenido = result.getResponseBody().blockFirst();

        assertEquals(informe.getContenido(), informeObtenido.getContenido());
        assertEquals(informe.getId(), informeObtenido.getId());
        assertTrue(informeObtenido.getPrediccion().startsWith(informe.getPrediccion()));
    }

    
    @Test
    @DisplayName("Crear un informe y borrarlo correctamente por ID")
    public void crearInformePost_seBorraConDelete() {

        //Crea el medico
        client.post().uri("/medico")
            .body(Mono.just(medico), Medico.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        //Crea el paciente
        client.post().uri("/paciente")
            .body(Mono.just(paciente), Paciente.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        File uploadFile = new File("./src/test/resources/no_healthty.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Crea un informe
        client.post().uri("/informe")
            .body(Mono.just(informe), Informe.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();
        
        //Elimina el informe
        FluxExchangeResult<String> result = client.delete().uri("/informe/1")
            .exchange()
            .expectStatus().isNoContent().returnResult(String.class);
     
        String response = result.getResponseBody().blockFirst();
     
        assertEquals(null, response);
    }


    @Test
    @DisplayName("Obtener una lista de informes de una imagen")
    public void obtenerInformesDeImagen() {

        //Crea el medico
        client.post().uri("/medico")
            .body(Mono.just(medico), Medico.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        //Crea el paciente
        client.post().uri("/paciente")
            .body(Mono.just(paciente), Paciente.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        File uploadFile = new File("./src/test/resources/no_healthty.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Crea un informe
        client.post().uri("/informe")
            .body(Mono.just(informe), Informe.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody().returnResult();

        //Obtiene la lista de informes de la imagen con ID 1
        FluxExchangeResult<Informe> result = client.get().uri("/informe/imagen/1")
            .exchange()
            .expectStatus().isOk().returnResult(Informe.class);

        Informe informeObtenido = result.getResponseBody().blockFirst();

        assertEquals(informe.getContenido(), informeObtenido.getContenido());
        assertEquals(informe.getId(), informeObtenido.getId());
        assertTrue(informeObtenido.getPrediccion().startsWith(informe.getPrediccion()));
    }
}
