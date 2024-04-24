package com.backend1.pensionat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rum {

    @Id
    @GeneratedValue
    private long id;
    private int nummer;
    private int storlek;
    private String typ;
    private int kapacitet;
    private double pris;

    public Rum(int nummer, int storlek, String typ, int kapacitet, double pris) {
        this.nummer = nummer;
        this.storlek = storlek;
        this.typ = typ;
        this.kapacitet = kapacitet;
        this.pris = pris;
    }
}
