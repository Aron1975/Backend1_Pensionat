package com.backend1.pensionat.services;

import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.models.Kund;


import java.util.List;

public interface KundService {


    public List<DetailedKundDto> getAllKunder();

    public DetailedKundDto kundToDetailedKundDto(Kund k);

    public Kund detailedKundDtoToKund(DetailedKundDto k);

    public KundDto kundToKundDto(Kund k);





}
