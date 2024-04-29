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

    @NotBlank(message = "SSN number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "Mobile number must be between 10 and 12 digits")
    private String ssn;
    @NotBlank(message = "First name cannot be empty")
    @Size(max = 50, message = "First name must be a maximum of 50 characters")
    private String förnamn;
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name must be a maximum of 50 characters")
    private String efternamn;
    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "Mobile number must be between 10 and 12 digits")
    private String mobilnummer;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Address cannot be empty")
    private String adress;
    @NotBlank(message = "City cannot be empty")
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
