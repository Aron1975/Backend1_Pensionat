package com.backend1.pensionat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Kund {

    @Id
    @GeneratedValue
    private long id;
    private String förnamn;
    private String efternamn;
    private String mobilnummer;
    private String email;
    private String adress;
    private String stad;

    public Kund(String stad, String adress, String email, String mobilnummer, String efternamn, String förnnamn) {
        this.stad = stad;
        this.adress = adress;
        this.email = email;
        this.mobilnummer = mobilnummer;
        this.efternamn = efternamn;
        this.förnamn = förnnamn;
    }
}
