package com.backend1.pensionat.services;

import com.backend1.pensionat.dtos.RumDto;
import com.backend1.pensionat.models.Rum;

import java.time.LocalDate;
import java.util.List;

public interface RumService {

    public List<RumDto> getAllRum();

    public RumDto rumToRumDto(Rum r);

    public List<RumDto> getAvailableRum(int antal);

    
}
