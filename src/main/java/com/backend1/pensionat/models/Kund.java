package com.backend1.pensionat.models;

import jakarta.persistence.*;
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
public class Kund {

    @Id
    @GeneratedValue
    private long id;
    private String ssn;
    private String förnamn;
    private String efternamn;
    private String mobilnummer;
    private String email;
    private String adress;
    private String stad;

    public Kund(String stad, String adress, String email, String mobilnummer, String efternamn, String förnnamn, String ssn) {
        this.stad = stad;
        this.adress = adress;
        this.email = email;
        this.mobilnummer = mobilnummer;
        this.efternamn = efternamn;
        this.förnamn = förnnamn;
        this.ssn = ssn;
    }

    @OneToMany(mappedBy = "kund")
    private List<Bokning> bokningList = new ArrayList<>();
}
