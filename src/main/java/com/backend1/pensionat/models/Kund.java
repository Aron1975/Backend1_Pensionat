package com.backend1.pensionat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Ange ditt personnumer")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "Personnumret måste vara mellan 10 och 12 siffror")
    private String ssn;

    @NotBlank(message = "Ange ditt förnamn")
    @Pattern(regexp = "(^$|^[A-Za-zåäöÅÄÖ]+$)", message = "Förnamnet får endast innehålla bokstäver")
    @Size(max = 50, message = "Förnamnet får ha maximalt 50 tecken")
    private String förnamn;

    @NotBlank(message = "Ange ditt efternamn")
    @Pattern(regexp = "(^$|^[A-Za-zåäöÅÄÖ]+$)", message = "Efternamnet får endast innehålla bokstäver")
    @Size(max = 50, message = "Efternamnet får ha maximalt 50 tecken")
    private String efternamn;

    @NotBlank(message = "Ange ditt mobilnummer")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "Mobilnumret måste vara mellan 10 och 12 siffror")
    private String mobilnummer;

    @NotBlank(message = "Ange din e-postadress")
    @Email(message = "E-postadressen måste vara giltig")
    private String email;

    @NotBlank(message = "Ange din adress")
    private String adress;

    @NotBlank(message = "Ange din stad")
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
