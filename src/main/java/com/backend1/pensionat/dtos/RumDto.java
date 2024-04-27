package com.backend1.pensionat.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RumDto {
    private long id;
    private String rumTyp;
    private double pris;
    private int storlek;
    private int kapacitet;
    private int nummer;
}
