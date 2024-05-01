package com.backend1.pensionat;


import com.backend1.pensionat.controllers.KundController;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.KundService;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import javax.swing.text.html.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//testa anrop i controller, och mockdata i service?

@SpringBootTest
@AutoConfigureMockMvc
public class KundControllerTestDeep {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private KundServiceImpl mockKundService;

    @MockBean
    private KundRepo mockKundRepo;

   // @MockBean
   // private DetailedKundDto mockKund;

   //@MockBean
   //private KundController kController;

    List<DetailedKundDto> expectedResponseList = new ArrayList<>();

   // @MockBean
   // DetailedKundDto mockKund = new DetailedKundDto();

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

    /*
    private long id;
    private String ssn;
    private String förnamn;
    private String efternamn;
    private String adress;
    private String stad;
    private String mobilnummer;
    private String email;
     */


    @BeforeEach
    public void init() {  //kanske har mockdata för service istället? controller går ju mot service
        Kund k1 = new Kund("Huddinge", "Stockholmsvägen 23",
                "karlsson@hotmail.com", "0762272212", "Karlsson", "Karl", "12345");
        Kund k2 = new Kund("Tungelsta", "Vretavägen 22",
                "hotmail@hotmail.com", "076222233", "Levi", "Maja", "54321");
        k1.setId(1L);
        k2.setId(2L);


       /* mockKund.setId(1L);
        mockKund.setSsn("123");
        mockKund.setFörnamn("hej");
        mockKund.setEfternamn("där");
        mockKund.setEmail("email@email.com");
        mockKund.setAdress("stad 34");
        mockKund.setMobilnummer("321");
        mockKund.setStad("huddinge"); */

        //expectedResponseList = Arrays.asList(new DetailedKundDto(k1), new DetailedKundDto(k2));

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


        this.mvc.perform(get("/kund/all"))                       //allt fungerar, dock används inte testdata
                .andExpect(status().isOk())
                .andExpect(view().name("/allaKunder"))
                .andExpect(model().attributeExists("responseList"))
                .andExpect(model().attributeExists("kat"))
                .andExpect(model().attributeExists("titel"))
                .andExpect(model().attribute("responseList", expectedResponseList))
                .andExpect(model().attribute("kat", "kunder"))
                .andExpect(model().attribute("titel", "Kund"));
    }

/*
    @PostMapping("/add")
    public String sparaKund(DetailedKundDto kund) {
        kundServiceImp.spara(kund);
        return "redirect:/kund/all";
    }
*/

    @Test            //funkar ej
    public void testSparaKund() throws Exception {

        DetailedKundDto mockKund = new DetailedKundDto();
        mockKund.setId(1L);
        mockKund.setSsn("123");
        mockKund.setFörnamn("hej");
        mockKund.setEfternamn("där");
        mockKund.setEmail("email@email.com");
        mockKund.setAdress("stad 34");
        mockKund.setMobilnummer("321");
        mockKund.setStad("huddinge");



        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(mockKund);

        MvcResult result = mvc.perform(post("/kund/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent.getBytes()))
                .andExpect(status().is3xxRedirection())
                .andReturn();


        String redirectUrl = result.getResponse().getRedirectedUrl();
        assertEquals("/kund/all", redirectUrl);

        verify(mockKundService).spara(mockKund);
    }




}
