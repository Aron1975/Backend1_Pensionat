package com.backend1.pensionat;

import com.backend1.pensionat.controllers.KundController;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.services.KundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KundControllerTest {


    @Autowired
    private KundController kController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value(value="${local.server.port}")
    private int port;

    @Test
    public void contextLoads() throws Exception {               //testar att kundcontrollern ej är null
        assertThat(kController).isNotNull();


    }


    @Test
    public void callKundController() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",     //testar att sträng finns i index.html
                String.class)).contains("Välkommen till Pensionatet!");

    }



}








    /*

    @Mock
    private KundService kundService;

    @InjectMocks
    private KundController kundController;

    private MockMvc mockMvc;

    @Test
    public void testGetAllKunder() throws Exception {
        // Mocking the service response
        List<DetailedKundDto> mockResponseList = new ArrayList<>();
        // Add mocked data to mockResponseList if needed

        when(kundService.getAllKunder()).thenReturn(mockResponseList);

        // Setting up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(kundController).build();

        // Perform GET request and verify the response
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("/allaKunder"))
                .andExpect(model().attributeExists("responseList"))
                .andExpect(model().attributeExists("kat"))
                .andExpect(model().attributeExists("titel"))
                .andExpect(model().attribute("responseList", mockResponseList))
                .andExpect(model().attribute("kat", "kunder"))
                .andExpect(model().attribute("titel", "Kund"));
    }
}








*/



/*
@SpringBootTest
@AutoConfigureMockMvc
class KundControllerTest {


    @InjectMocks
    private MockMvc mockMvc;
    private KundController kundController;

    @Mock
    private KundService mockService;


    @Test
    public void testGetAllKunder() throws Exception {

        List<DetailedKundDto> mockResponseList = new ArrayList<>();

        when(mockService.getAllKunder()).thenReturn(mockResponseList);

        mockMvc = MockMvcBuilders.standaloneSetup(kundController).build();

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("/allaKunder"))
                .andExpect(model().attributeExists("responseList"))
                .andExpect(model().attributeExists("kat"))
                .andExpect(model().attributeExists("titel"))
                .andExpect(model().attribute("responseList", mockResponseList))
                .andExpect(model().attribute("kat", "kunder"))
                .andExpect(model().attribute("titel", "Kund"));

    }
}
 */ //