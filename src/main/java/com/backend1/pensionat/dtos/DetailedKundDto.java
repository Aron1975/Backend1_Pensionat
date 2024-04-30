package com.backend1.pensionat.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "SSN number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "SSN number must be between 10 and 12 digits")
    private String ssn;

    @NotBlank(message = "First name cannot be empty")
    @Pattern(regexp = "(^$|^[A-Za-zåäöÅÄÖ]+$)", message = "First name must only contain letters")
    @Size(max = 50, message = "First name must be a maximum of 50 characters")
    private String förnamn;

    @NotBlank(message = "Last name cannot be empty")
    @Pattern(regexp = "(^$|^[A-Za-zåäöÅÄÖ]+$)", message = "Last name must only contain letters")
    @Size(max = 50, message = "Last name must be a maximum of 50 characters")
    private String efternamn;

    @NotBlank(message = "Address cannot be empty")
    private String adress;

    @NotBlank(message = "City cannot be empty")
    private String stad;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10,12})", message= "Mobile number must be between 10 and 12 digits")
    private String mobilnummer;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;


}
