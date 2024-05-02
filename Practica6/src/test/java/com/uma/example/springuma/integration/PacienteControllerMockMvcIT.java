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
import com.uma.example.springuma.model.Paciente;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.integration.base.AbstractIntegration;


class PacienteControllerMockMvcIT extends AbstractIntegration {

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

    public Paciente crearPaciente ()throws Exception
    {
        Medico medico = crearMedico();

        Paciente paciente = new Paciente();
        paciente.setDni("1234ABC");
        paciente.setNombre("juan");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        paciente.setMedico(medico);
        
        // Crear paciente
        this.mockMvc.perform(post("/paciente")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(paciente)));

            return paciente;
    }

    @Test
    @DisplayName("Crear paciente")
    void testCrearPaciente() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setDni("123ABC");
        paciente.setNombre("juan");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        


        // Crear paciente
        this.mockMvc.perform(post("/paciente")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(paciente)))
            .andExpect(status().isCreated())
            .andExpect(status().is2xxSuccessful());
            
        // Obtenemos el Paciente
        this.mockMvc.perform(get("/paciente/{id}",paciente.getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value(paciente.getDni()));
    }

    @Test
    @DisplayName("Actualizar paciente")
    void testActualizarPaciente() throws Exception {
        Paciente paciente = crearPaciente();

        // Actualizar paciente
        paciente.setDni("12345ABC");

        this.mockMvc.perform(put("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().isNoContent())
        .andExpect(status().is2xxSuccessful());

        // Obtenemos el Paciente
        this.mockMvc.perform(get("/paciente/{id}",paciente.getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value("12345ABC"));
    }

    @Test
    @DisplayName("Obtener paciente")
    void testObtenerPaciente() throws Exception {
        Paciente paciente = crearPaciente();

        // Obtenemos el Paciente
        this.mockMvc.perform(get("/paciente/{id}",paciente.getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.dni").value(paciente.getDni()));
    }

    @Test
    @DisplayName("Eliminar paciente")
    void testEliminarPaciente() throws Exception {
        Paciente paciente = crearPaciente();

        // Eliminar médico
        this.mockMvc.perform(delete("/paciente/{id}", paciente.getId()))
        .andDo(print())
        .andExpect(status().isOk());

        // Obtenemos el Paciente en busca del error
        this.mockMvc.perform(get("/paciente/{id}", paciente.getId()))
        .andDo(print())
        .andExpect(status().isInternalServerError());

    }

    @Test
    @DisplayName("Obtener paciente asignado a medico")
    void testObtenerPacienteAsignado() throws Exception {
        Paciente paciente = crearPaciente();

        // Obtenemos el Medico
        this.mockMvc.perform(get("/paciente/medico/{id}", paciente.getMedico().getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$[0].dni").value(paciente.getDni()));
    }

    @Test
    @DisplayName("Obtener lista de pacientes asignados a un medico")
    void testObtenerListaPacientesAsignados() throws Exception {
        Medico medico = crearMedico();

        Paciente paciente = new Paciente();
        paciente.setDni("1234ABC");
        paciente.setNombre("juan");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        paciente.setMedico(medico);
        
        // Crear paciente
        this.mockMvc.perform(post("/paciente")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(paciente)));

            Paciente paciente2 = new Paciente();
            paciente2.setDni("1234ABCD");
            paciente2.setNombre("juan");
            paciente2.setEdad(0);
            paciente2.setCita("21/12/2033");
            paciente2.setId(2);
            paciente2.setMedico(medico);
            
            // Crear paciente
            this.mockMvc.perform(post("/paciente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente2)));

        // Obtenemos el Medico
        this.mockMvc.perform(get("/paciente/medico/{id}", paciente.getMedico().getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$",hasSize(2)))
        .andExpect(jsonPath("$[0].dni").value(paciente.getDni()))
        .andExpect(jsonPath("$[1].dni").value(paciente2.getDni()));
    }

    @Test
    @DisplayName("Borrar paciente asignado a medico")
    void testBorrarPacienteAsignado() throws Exception {
        Medico medico = crearMedico();

        Paciente paciente = new Paciente();
        paciente.setDni("1234ABC");
        paciente.setNombre("juan");
        paciente.setEdad(0);
        paciente.setCita("21/12/2033");
        paciente.setId(1);
        paciente.setMedico(medico);
        
        // Crear paciente
        this.mockMvc.perform(post("/paciente")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(paciente)));

            Paciente paciente2 = new Paciente();
            paciente2.setDni("1234ABCD");
            paciente2.setNombre("juan");
            paciente2.setEdad(0);
            paciente2.setCita("21/12/2033");
            paciente2.setId(2);
            paciente2.setMedico(medico);
            
            // Crear paciente
            this.mockMvc.perform(post("/paciente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente2)));

        // Eliminamos uno de los dos pacientes
        this.mockMvc.perform(delete("/paciente/{id}", paciente.getId()))
        .andDo(print())
        .andExpect(status().isOk());

        // Obtenemos el Medico
        this.mockMvc.perform(get("/paciente/medico/{id}", paciente2.getMedico().getId()))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0].dni").value(paciente2.getDni()));

    }
    
}