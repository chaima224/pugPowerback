package com.example.ElectricStations.services;

import com.example.ElectricStations.entities.Borne;
import com.example.ElectricStations.entities.Stations;
import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Disponibilite;
import com.example.ElectricStations.enums.Mode;
import com.example.ElectricStations.repositories.BorneRepository;
import com.example.ElectricStations.repositories.StationsRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StationService {
    @Autowired
    private MongoTemplate mongoTemplate;
 private final StationsRepository stationsRepository;
 private  final BorneRepository borneRepository;

    public StationService(StationsRepository stationsRepository, BorneRepository borneRepository) {
        this.stationsRepository = stationsRepository;
        this.borneRepository = borneRepository;
    }

    public String save(Stations station)
    {
        return  stationsRepository.save(station).getId();
    }
    public Stations findById(String id)
    { return stationsRepository.findById(id)
            .orElse(null);
    }
    public List<Stations> findAll()
    {
        return  stationsRepository.findAll();
    }
    public List<Stations> getAllStations() {
        return stationsRepository.findAll();
    }

    public ResponseEntity<?> updateStation(String id, Stations newStation) {
        // Vérifier si la station avec cet ID existe déjà
        Optional<Stations> existingStation = stationsRepository.findById(id);
        if (existingStation.isPresent()) {
            // Mettre à jour les propriétés de la station existante avec les nouvelles valeurs
            Stations updatedStation = existingStation.get();
            updatedStation.setName(newStation.getName());
            updatedStation.setLatitude(newStation.getLatitude());
            updatedStation.setLongitude(newStation.getLongitude());
            updatedStation.setMoyNote(newStation.getMoyNote());
            updatedStation.setOuverture(newStation.getOuverture());
            updatedStation.setFermeture(newStation.getFermeture());
            updatedStation.setBornes(newStation.getBornes());

            // Enregistrer les modifications dans la base de données
            stationsRepository.save(updatedStation);

            // Retourner un objet JSON contenant un message de succès
            JSONObject response = new JSONObject();
            response.put("success", true);
            response.put("message", "La station a été modifiée avec succès.");
            return ResponseEntity.ok(response.toString());
        } else {
            // Retourner un objet JSON contenant un message d'erreur
            JSONObject response = new JSONObject();
            response.put("success", false);
            response.put("message", "La station avec l'ID " + id + " n'existe pas.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.toString());
        }
    }


    public void delete(String id)

    {
        stationsRepository.deleteById(id);
    }
    public boolean isStationAvailable(String stationId, LocalDateTime dateTime) {
        Optional<Stations> optionalStation = Optional.ofNullable(mongoTemplate.findById(stationId, Stations.class));
        if (optionalStation.isPresent()) {
            Stations station = optionalStation.get();
            LocalDateTime openingDateTime = station.getOuverture();
            LocalDateTime closingDateTime = station.getFermeture();
            return (dateTime.isAfter(openingDateTime) || dateTime.isEqual(openingDateTime))
                    && (dateTime.isBefore(closingDateTime) || dateTime.isEqual(closingDateTime));
        } else {
            throw new RuntimeException("Station not found");
        }
    }
   /* public boolean isBorneExistInStation(String stationId, String borneId) {
        Optional<Stations> optionalStation = stationsRepository.findById(stationId);
        if (optionalStation.isPresent()) {
            Stations station = optionalStation.get();
            List<Borne> bornes = station.getBornes();
            for (Borne borne : bornes) {
                if (borne.getId().equals(borneId)) {
                    return true;
                }
            }
        }
        return false;
    }*/
   public boolean isBorneExistInStation(String stationId, String borneId) {
       Optional<Stations> optionalStation = stationsRepository.findById(stationId);
       if (optionalStation.isPresent()) {
           Stations station = optionalStation.get();
           List<Borne> bornes = station.getBornes();
           for (Borne borne : bornes) {
               if (borne.getId().equals(borneId)) {
                   return true;
               }
           }
       }
       return false;
   }



//    public List<Stations> findStationsNearby(double longitude, double latitude, double distance) {
//        Point location = new Point(longitude, latitude);
//        Distance maxDistance = new Distance(distance, Metrics.KILOMETERS);
//        return stationsRepository.findByLocationNear(location, maxDistance);
//    }
//






}
