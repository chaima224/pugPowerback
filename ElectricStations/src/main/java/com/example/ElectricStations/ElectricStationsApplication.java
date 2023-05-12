package com.example.ElectricStations;

import com.example.ElectricStations.entities.Borne;
import com.example.ElectricStations.entities.Stations;
import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Mode;
import com.example.ElectricStations.repositories.BorneRepository;
import com.example.ElectricStations.repositories.StationsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
public class ElectricStationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricStationsApplication.class, args);
		System.out.println("success");
	}

/*	@Bean
	public CommandLineRunner(StationsRepository stationsRepository) {
		return args -> {
			var station = Stations.builder()
					.latitude(102)
					.longitude(103.2)
					.moyNote(5)
					.build();
			stationsRepository.insert(station);

		};

	}*/
@Bean
PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();

}

}
