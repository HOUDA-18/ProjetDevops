package tn.esprit.spring.kaddem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContratRestController.class)
public class ContratRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IContratService contratService;

    @Autowired
    private ObjectMapper objectMapper;

    private Contrat sampleContrat;

    @BeforeEach
    void setUp() {
        sampleContrat = new Contrat();
        sampleContrat.setIdContrat(1);
        sampleContrat.setDateDebutContrat(new Date());
        // Set other fields as needed
    }
    @Test
    public void testGetAllContrats() throws Exception {
        Mockito.when(contratService.retrieveAllContrats())
                .thenReturn(Arrays.asList(sampleContrat));

        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].idContrat").value(1));
    }
}