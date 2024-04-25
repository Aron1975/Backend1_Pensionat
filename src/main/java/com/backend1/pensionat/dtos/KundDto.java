package com.backend1.pensionat.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KundDto {

        private long id;
        private String ssn;
        private String förnamn;
        private String efternamn;
    }

