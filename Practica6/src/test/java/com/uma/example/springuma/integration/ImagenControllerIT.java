package com.uma.example.springuma.integration;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class ImagenControllerIT {

    @LocalServerPort
    private Integer port;

    private WebTestClient client;

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
    }

    @Test
    @DisplayName("Subir la imagen la crea correctamente")
    public void subirImagen_debeResponderCorrecto() {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        FluxExchangeResult<String> responseBody = client.post()
            .uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        String result = responseBody.getResponseBody().blockFirst();
        
        assertEquals("{\"response\" : \"file uploaded successfully : healthy.png\"}", result);
    }

    @Test
    @DisplayName("Buscar una imagen por id devuelve la imagen")
    public void buscarImagenPorId_debeResponderCorrecto() throws IOException {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Busca la imagen
        FluxExchangeResult<byte[]> result = client.get().uri("/imagen/1")
            .exchange()
            .expectStatus().isOk().returnResult(byte[].class);
        
        byte[] imagenObtained = result.getResponseBody().blockFirst();
        
        assertEquals((new FileSystemResource(uploadFile)).getContentAsByteArray(), imagenObtained);
    }


    @Test
    @DisplayName("Buscar la info de una imagen por id devuelve la imagen")
    public void buscarInfoImagenPorId_debeResponderCorrecto() throws IOException {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Busca la imagen
        FluxExchangeResult<Imagen> result = client.get().uri("/imagen/info/1")
            .exchange()
            .expectStatus().isOk().returnResult(Imagen.class);
        
        Imagen imagenObtained = result.getResponseBody().blockFirst();
        
        assertEquals(1, imagenObtained.getId());
        assertEquals("healthy.png", imagenObtained.getNombre());
        assertEquals(1, imagenObtained.getPaciente().getId());
    }


    @Test
    @DisplayName("Predecir una imagen sana devuelve la prediccion Not cancer")
    public void predecirImagenSana_debeResponderNotCancer() {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Hacer la predicción
        FluxExchangeResult<String> result = client.get().uri("/imagen/predict/1")
            .exchange()
            .expectStatus().isOk().returnResult(String.class);
        
        String prediction = result.getResponseBody().blockFirst();
        
        assertTrue(prediction.startsWith("Not cancer (label 0)"));
    }


    @Test
    @DisplayName("Predecir una imagen con cáncer devuelve la prediccion Cancer")
    public void predecirImagenCancer_debeResponderCancer() {

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

        //Hacer la predicción
        FluxExchangeResult<String> result = client.get().uri("/imagen/predict/1")
            .exchange()
            .expectStatus().isOk().returnResult(String.class);
        
        String prediction = result.getResponseBody().blockFirst();
        
        assertTrue(prediction.startsWith("Cancer (label 1)"));
    }


    @Test
    @DisplayName("Buscar las imagenes de un paciente devuelve las imagenes")
    public void buscarImagenesPorPaciente_debeResponderCorrecto() throws IOException {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        File uploadFile2 = new File("./src/test/resources/no_healthty.png");

        MultipartBodyBuilder builder2 = new MultipartBodyBuilder();
        builder2.part("image", new FileSystemResource(uploadFile2));
        builder2.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder2.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Busca las imagenes
        FluxExchangeResult<Imagen> result = client.get().uri("/imagen/paciente/1")
            .exchange()
            .expectStatus().isOk().returnResult(Imagen.class);
        
        //List<Imagen> imagenesObtained = result.getResponseBody().blockFirst();

        //assertEquals(2, imagenesObtained.size());
        //assertEquals(1, imagenesObtained.get(0).getId());
        //assertEquals(2, imagenesObtained.get(1).getId());
    }

    @Test
    @DisplayName("Eliminar una imagen por id devuelve no content")
    public void eliminarImagenPorId_debeResponderNoContent() throws IOException {

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

        File uploadFile = new File("./src/test/resources/healthy.png");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(uploadFile));
        builder.part("paciente", paciente);

        //Crea una imagen
        client.post().uri("/imagen")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchange()
            .expectStatus().is2xxSuccessful().returnResult(String.class);

        //Elimina la imagen
        FluxExchangeResult<String> result = client.delete().uri("/imagen/1")
            .exchange()
            .expectStatus().isNoContent().returnResult(String.class);
        
        String response = result.getResponseBody().blockFirst();
        
        assertEquals(null, response);
    }
}