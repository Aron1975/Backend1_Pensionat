package com.backend1.pensionat;


import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KundServiceImplTest {


    @Mock
    private BokningRepo bokningRepo;
    @Mock
    private KundRepo kundRepo;


    private KundServiceImpl kundService = new KundServiceImpl(kundRepo, bokningRepo);



    /* test av följande
    public DetailedKundDto kundToDetailedKundDto(Kund k) {
        return DetailedKundDto.builder().id(k.getId()).ssn(k.getSsn()).förnamn(k.getFörnamn())
                .efternamn(k.getEfternamn()).mobilnummer(k.getMobilnummer()).email(k.getEmail())
                .adress(k.getAdress()).stad(k.getStad()).build();

    }
     */

    @Test
    void testKundToDetailedKundDto() {
        Kund kund = new Kund("Huddinge", "Stockholmsvägen 23",
                "karlsson@hotmail.com", "0762272212", "Karlsson", "Karl", "12345");
        kund.setId(1L);

        DetailedKundDto detailedKundDto = kundService.kundToDetailedKundDto(kund);

        assertEquals(kund.getId(), detailedKundDto.getId());
        assertEquals(kund.getSsn(), detailedKundDto.getSsn());
        assertEquals(kund.getFörnamn(), detailedKundDto.getFörnamn());
        assertEquals(kund.getEfternamn(), detailedKundDto.getEfternamn());
        assertEquals(kund.getMobilnummer(), detailedKundDto.getMobilnummer());
        assertEquals(kund.getEmail(), detailedKundDto.getEmail());
        assertEquals(kund.getAdress(), detailedKundDto.getAdress());
        assertEquals(kund.getStad(), detailedKundDto.getStad());
    }

/* test av följade
public Kund detailedKundDtoToKund(DetailedKundDto dto) {
        Kund kund = new Kund();
        kund.setId(dto.getId());
        kund.setSsn(dto.getSsn());
        kund.setFörnamn(dto.getFörnamn());
        kund.setEfternamn(dto.getEfternamn());
        kund.setAdress(dto.getAdress());
        kund.setStad(dto.getStad());
        kund.setMobilnummer(dto.getMobilnummer());
        kund.setEmail(dto.getEmail());
        return kund;
    }
 */

   @Test
    void testDetailedKundDtoToKund() {

       DetailedKundDto detailedKundDto = DetailedKundDto.builder().id(1L).ssn("123456").förnamn("karl")
               .efternamn("karlsson").adress("Vegavägen 23").stad("somewhere").mobilnummer("1235-3234")
               .email("john@hotmail.com").build();


       Kund kund = kundService.detailedKundDtoToKund(detailedKundDto);

       assertEquals(detailedKundDto.getId(), kund.getId());
       assertEquals(detailedKundDto.getSsn(), kund.getSsn());
       assertEquals(detailedKundDto.getFörnamn(), kund.getFörnamn());
       assertEquals(detailedKundDto.getEfternamn(), kund.getEfternamn());
       assertEquals(detailedKundDto.getAdress(), kund.getAdress());
       assertEquals(detailedKundDto.getStad(), kund.getStad());
       assertEquals(detailedKundDto.getMobilnummer(), kund.getMobilnummer());
       assertEquals(detailedKundDto.getEmail(), kund.getEmail());

   }


   /* test av följande
   public List<DetailedKundDto> getAllKunder() {
        return kundRepo.findAll().stream().map(k -> kundToDetailedKundDto(k)).toList();
    }

    */


    @Test
    void testgetAllKunder() {

        Kund kund = new Kund("Huddinge", "Stockholmsvägen 23",
                "karlsson@hotmail.com", "0762272212", "Karlsson", "Karl", "12345");
        kund.setId(1L);

        when(kundRepo.findAll()).thenReturn(Arrays.asList(kund));
        kundService = new KundServiceImpl(kundRepo, bokningRepo);
        List<DetailedKundDto> allakunder = kundService.getAllKunder();

        assertTrue(allakunder.size() == 1);     //blir fel om man tar 2



    }




/* svår metod att testa? returnar void o sparar i databas
 public void spara(DetailedKundDto k){
        Kund kund = detailedKundDtoToKund(k);
        kundRepo.save(kund);

    }
 */




    /*



    @Test
    void testSpara() {

        DetailedKundDto detailedKundDto = new DetailedKundDto();
        detailedKundDto.setSsn("123456789");

        Kund kund = new Kund();

        when(kundService.detailedKundDtoToKund(detailedKundDto)).thenReturn(kund);
        kundService.spara(detailedKundDto);

        verify(kundRepo).save(kund);
    }


*/







}
