package com.backend1.pensionat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rum {

    @Id
    @GeneratedValue
    private long id;

    @Min(value = 1, message = "Rummets nummer måste vara större än 0.")
    private int nummer;

    @Min(value = 10, message = "Storleken måste vara minst 10 kvm.")
    private int storlek;

    @NotBlank(message = "Typ av rum får inte vara tomt.")
    private String typ;

    @Min(value = 1, message = "Kapaciteten måste vara minst 1.")
    private int kapacitet;

    @DecimalMin(value = "500.00", message = "Priset måste vara minst 500 kr per natt.")
    private double pris;

    public Rum(int nummer, int storlek, String typ, int kapacitet, double pris) {
        this.nummer = nummer;
        this.storlek = storlek;
        this.typ = typ;
        this.kapacitet = kapacitet;
        this.pris = pris;
    }

    @OneToMany(mappedBy = "rum")
    private List<Bokning> bokningList = new ArrayList<>();
}
