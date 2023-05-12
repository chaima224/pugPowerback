package com.example.ElectricStations.repositories;
import com.example.ElectricStations.entities.Borne;
import com.example.ElectricStations.entities.Stations;
import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Disponibilite;
import com.example.ElectricStations.enums.Mode;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StationsRepository extends MongoRepository<Stations,String> {

    //List<Stations> findByLocationNear(Point point, Distance distance);

    StationsRepository findByBornesContains(Borne borne);
    List<Stations> findByBornes(String id);
}
