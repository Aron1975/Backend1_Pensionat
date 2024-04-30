package com.backend1.pensionat.dtos;

import com.backend1.pensionat.models.Kund;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedKundDto {

    private long id;
    private String ssn;
    private String förnamn;
    private String efternamn;
    private String adress;
    private String stad;
    private String mobilnummer;
    private String email;



}
