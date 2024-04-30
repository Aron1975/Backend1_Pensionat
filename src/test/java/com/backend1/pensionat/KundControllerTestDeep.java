package com.backend1.pensionat;


import com.backend1.pensionat.controllers.KundController;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@SpringBootTest
@AutoConfigureMockMvc
public class KundControllerTestDeep {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private KundServiceImpl mockKundService;

    @MockBean
    private KundRepo mockKundRepo;



    List<DetailedKundDto> expectedResponseList = new ArrayList<>();
    /*
    private long id;
    private String ssn;
    private String förnamn;
    private String efternamn;
    private String mobilnummer;
    private String email;
    private String adress;
    private String stad;


    public Kund(String stad, String adress, String email, String mobilnummer, String efternamn, String förnnamn, String ssn) {
        this.stad = stad;
        this.adress = adress;
        this.email = email;
        this.mobilnummer = mobilnummer;
        this.efternamn = efternamn;
        this.förnamn = förnnamn;
        this.ssn = ssn;
    }
     */

    @BeforeEach
    public void init() {
        Kund k1 = new Kund("Huddinge", "Stockholmsvägen 23",
                "karlsson@hotmail.com", "0762272212", "Karlsson", "Karl", "12345");
        Kund k2 = new Kund("Tungelsta", "Vretavägen 22",
                "hotmail@hotmail.com", "076222233", "Levi", "Maja", "54321");
        k1.setId(1L);
        k2.setId(2L);

        when(mockKundRepo.findById(1L)).thenReturn(Optional.of(k1));
        when(mockKundRepo.findById(2L)).thenReturn(Optional.of(k2));
        when(mockKundRepo.findAll()).thenReturn(Arrays.asList(k1, k2));
        when(mockKundService.getAllKunder()).thenReturn(expectedResponseList);
    }
/*
    @RequestMapping("/all")
    public String getAllKunder(Model model) {
        List<DetailedKundDto> responseList = kundService.getAllKunder();
        model.addAttribute("responseList", responseList);
        model.addAttribute("kat", "kunder");
        model.addAttribute("titel", "Kund");
        return "/allaKunder";
    }
 */

    @Test
    void getAllKunder() throws Exception {


        this.mvc.perform(get("/kund/all"))
                .andExpect(status().isOk())                      //Fungerar
                .andExpect(view().name("/allaKunder"));
    }



}
