package com.backend1.pensionat;

import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.repos.RumRepo;
import com.backend1.pensionat.services.impl.BokningServiceImpl;
import com.backend1.pensionat.services.impl.KundServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BokningServiceImplTest {


    @Mock
    BokningRepo bokningRepo;

    @Mock
    KundRepo kundRepo;

    @Mock
    RumRepo rumRepo;

    BokningServiceImpl bokningServiceImpl = new BokningServiceImpl(bokningRepo, kundRepo, rumRepo);

    KundServiceImpl kundService = new KundServiceImpl(kundRepo, bokningRepo);


/*
private long id;
    private LocalDate bokningsDatum;
    private LocalDate startDatum;
    private LocalDate slutDatum;
    private int antalGäster;
    private int antalExtraSängar;
    private double totalPris;
 */

    /* test av följande

    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b) {
        return DetailedBokningDto.builder().id(b.getId()).bokningsDatum(b.getBokningsDatum()).startDatum(b.getStartDatum())
                .slutDatum(b.getSlutDatum()).antalGäster(b.getAntalGäster()).antalExtraSängar(b.getAntalExtraSängar())
                .totalPris(b.getTotalPris()).kund(new KundDto(b.getKund().getId(), b.getKund().getSsn(), b.getKund().getFörnamn(), b.getKund().getEfternamn()))
                .rum(new RumDto(b.getRum().getId(), b.getRum().getTyp(),b.getRum().getPris(), b.getRum().getStorlek(), b.getRum().getKapacitet(), b.getRum().getNummer())).build();

    }
     */

    @Test    //testar konvertering från bokning till detailedbokningdto
    void testBokningToDetailedBokningDto() {


        //ett sätt att mocka upp instanser för att testa bokningtodetailedbokningdto.
        Bokning bokning = mock(Bokning.class);
        when(bokning.getId()).thenReturn(1L);
        when(bokning.getBokningsDatum()).thenReturn(LocalDate.parse("2024-05-01"));

        Kund kund = mock(Kund.class);
        when(kund.getId()).thenReturn(1L);
        when(kund.getSsn()).thenReturn("123456789");

        Rum rum = mock(Rum.class);
        when(rum.getId()).thenReturn(1L);
        when(rum.getTyp()).thenReturn("Double");

        when(bokning.getKund()).thenReturn(kund);
        when(bokning.getRum()).thenReturn(rum);


        //anropar metoden
        DetailedBokningDto detailedBokningDto = bokningServiceImpl.bokningToDetailedBokningDto(bokning);

        assertEquals(bokning.getId(), detailedBokningDto.getId());
        assertEquals(bokning.getBokningsDatum(), detailedBokningDto.getBokningsDatum());

        assertEquals(bokning.getKund().getId(), detailedBokningDto.getKund().getId());
        assertEquals(detailedBokningDto.getKund().getSsn(), "123456789");

    }


}
