package com.backend1.pensionat.services;

import com.backend1.pensionat.dtos.BokningDto;
import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;

import java.time.LocalDate;
import java.util.List;

public interface BokningService {

    public List<DetailedBokningDto> getAllBokningar();

    public void deleteBokningWithoutKundId();
    public List<BokningDto> getAllBokningar2();

    BokningDto bokningToBokningDto(Bokning b);

    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b);
    public List<RumDto> getAvailableRumByDate(List<RumDto> availableRumByCapacity, LocalDate startDate, LocalDate stopDate);
    public void sparaBokning(DetailedBokningDto b);
    void sparaBokningTillKund(DetailedBokningDto b);
}
