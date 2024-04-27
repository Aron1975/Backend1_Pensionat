package com.backend1.pensionat.services;

import com.backend1.pensionat.dtos.DetailedBokningDto;
import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Bokning;

import java.util.List;

public interface BokningService {

    public List<DetailedBokningDto> getAllBokningar();
    public DetailedBokningDto bokningToDetailedBokningDto(Bokning b);
    public List<RumDto> getAvailableRumByDate(List<RumDto> availableRumByCapacity);
    public void sparaBokning(DetailedBokningDto b);
}
