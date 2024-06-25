package com.example.ClinicaOdontologicaBackend;

import com.example.ClinicaOdontologicaBackend.dto.TurnoDTO;
import com.example.ClinicaOdontologicaBackend.entity.Domicilio;
import com.example.ClinicaOdontologicaBackend.entity.Odontologo;
import com.example.ClinicaOdontologicaBackend.entity.Paciente;
import com.example.ClinicaOdontologicaBackend.entity.Turno;
import com.example.ClinicaOdontologicaBackend.service.OdontologoService;
import com.example.ClinicaOdontologicaBackend.service.PacienteService;
import com.example.ClinicaOdontologicaBackend.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologosIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos(){
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("MP10","Gina","Arias"));
    }

    @Test
    public void ListarTodosLosOdontologosTest() throws Exception{
        cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

    }
}