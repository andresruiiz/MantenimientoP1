package com.uma.example.springuma.integration;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.integration.base.AbstractIntegration;


class MedicoControllerIT extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public Medico crearMedico ()throws Exception
    {
        Medico medico = new Medico();
        medico.setDni("123ABC");
        medico.setNombre("juan");
        medico.setEspecialidad("Mamografias");
        medico.setId(1);
        


        // Crear médico
        this.mockMvc.perform(post("/medico")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(medico)));

        return medico;
    }

    @Test
    @DisplayName("Crear médico")
    void testCrearMedico() throws Exception {
        Medico medico = new Medico();
        medico.setDni("123ABC");
        medico.setNombre("juan");
        medico.setEspecialidad("Mamografias");
        medico.setId(1);
        
        // Crear médico
        this.mockMvc.perform(post("/medico")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(medico)))
            .andExpect(status().isCreated())
            .andExpect(status().is2xxSuccessful());
            
        // Obtenemos el Medico
        this.mockMvc.perform(get("/medico/1"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.dni").value(medico.getDni()));
    }

    @Test
    @DisplayName("Actualizar médico")
    void testActualizarMedico() throws Exception {
        Medico medico = crearMedico();

        // Actualizar médico
        medico.setDni("1234ABC");

        this.mockMvc.perform(put("/medico")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(medico)))
            .andExpect(status().isNoContent())
            .andExpect(status().is2xxSuccessful());

        // Obtenemos el Medico
        this.mockMvc.perform(get("/medico/1"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.dni").value("1234ABC"));
    }

    @Test
    @DisplayName("Obtener médico")
    void testObtenerMedico() throws Exception {
        Medico medico = crearMedico();

        // Obtenemos el Medico
        this.mockMvc.perform(get("/medico/1"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.dni").value(medico.getDni()));
    }

    @Test
    @DisplayName("Eliminar médico")
    void testEliminarMedico() throws Exception {
        Medico medico = crearMedico();

        // Eliminar médico
        this.mockMvc.perform(delete("/medico/{id}", medico.getId()))
            .andDo(print())
            .andExpect(status().isOk());

        // Obtenemos el Medico en busca del error
        this.mockMvc.perform(get("/medico/{id}", medico.getId()))
            .andDo(print())
            .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Obtener médico por dni")
    void testObtenerMedicoByDni() throws Exception {
        Medico medico = crearMedico();

        // Obtenemos el Medico
        this.mockMvc.perform(get("/medico/dni/{dni}",medico.getDni()))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.dni").value(medico.getDni()));
    }
}