package com.backend1.pensionat;

import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.models.Rum;
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

	/*

	@Bean
	public CommandLineRunner commandLineRunner(KundRepo kundRepo, RumRepo rumRepo) {

		return args -> {
			Kund k1 = new Kund("Stockholm", "Globen", "Kund@email.com", "07013456", "Kasper", "Bo", "701210-0012");
			kundRepo.save(k1);
			Kund k2 = new Kund("Malmö", "Brogatan", "Kund2@email.com", "12345678", "Plomeros", "Leifi", "650509-2365");
			kundRepo.save(k2);
			Kund k3 = new Kund("Göteborg", "Torget", "Kund@email.com", "98765432", "Close", "Glenn", "930604-9035");
			kundRepo.save(k3);



			Rum r1 = Rum.builder().nummer(1).storlek(10).typ("Enkelrum").pris(500).build();
			Rum r2 = Rum.builder().nummer(2).storlek(10).typ("Enkelrum").pris(500).build();
			Rum r3 = Rum.builder().nummer(3).storlek(10).typ("Enkelrum").pris(500).build();
			Rum r4 = Rum.builder().nummer(4).storlek(10).typ("Enkelrum").pris(500).build();
			Rum r5 = Rum.builder().nummer(5).storlek(15).typ("Dubbelrum").pris(800).build();
			Rum r6 = Rum.builder().nummer(6).storlek(15).typ("Dubbelrum").pris(800).build();
			Rum r7 = Rum.builder().nummer(7).storlek(15).typ("Dubbelrum").pris(800).build();
			Rum r8 = Rum.builder().nummer(8).storlek(20).typ("Dubbelrum Deluxe").pris(1000).build();
			Rum r9 = Rum.builder().nummer(9).storlek(20).typ("Dubbelrum Deluxe").pris(1000).build();
			Rum r10 = Rum.builder().nummer(10).storlek(20).typ("Dubbelrum Deluxe").pris(1000).build();

			rumRepo.save(r1);
			rumRepo.save(r2);
			rumRepo.save(r3);
			rumRepo.save(r4);
			rumRepo.save(r5);
			rumRepo.save(r6);
			rumRepo.save(r7);
			rumRepo.save(r8);
			rumRepo.save(r9);
			rumRepo.save(r10);

		};
	}
	
	 */
}
