package com.backend1.pensionat;

import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.repos.RumRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Backend1PensionatApplication {

	public static void main(String[] args) {
		SpringApplication.run(Backend1PensionatApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(KundRepo kundRepo, RumRepo rumRepo) {

		return args -> {
			Kund k1 = new Kund("Stockholm", "Globen", "Kund@email.com", "07013456", "Kasper", "Bo");
			kundRepo.save(k1);
			Kund k2 = new Kund("Malmö", "Brogatan", "Kund2@email.com", "12345678", "Plomeros", "Leifi");
			kundRepo.save(k2);
			Kund k3 = new Kund("Göteborg", "Torget", "Kund@email.com", "98765432", "Close", "Glenn");
			kundRepo.save(k3);

		};
	}
}
