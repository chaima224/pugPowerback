package com.example.ElectricStations.Controller;

import com.example.ElectricStations.entities.Borne;
import com.example.ElectricStations.entities.Stations;
import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Disponibilite;
import com.example.ElectricStations.enums.Mode;
import com.example.ElectricStations.repositories.StationsRepository;
import com.example.ElectricStations.services.StationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/electricStations")
public class StationController {

    private final StationService stationService;
    private final StationsRepository stationsRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    public StationController(StationService stationService, StationsRepository stationsRepository) {
        this.stationService = stationService;
        this.stationsRepository = stationsRepository;
    }




    @PostMapping
    public ResponseEntity<String> save(@RequestBody Stations station) {
        return ResponseEntity.ok(stationService.save(station));
    }

    @GetMapping
    public ResponseEntity<List<Stations>> findAll() {
        return ResponseEntity.ok(stationService.findAll());
    }

    @GetMapping("/{station-id}")
    public ResponseEntity<Stations> findById(@PathVariable("station-id") String id) {
        return ResponseEntity.ok(stationService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStation(@PathVariable String id, @RequestBody Stations newStation) {
        return stationService.updateStation(id, newStation);
    }


    @DeleteMapping("/{station-id}")
    public ResponseEntity<Void> delete(@PathVariable("station-id") String id) {
        stationService.delete(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/stations/{stationId}/availability")
    public boolean isStationAvailable(@PathVariable String stationId, @RequestParam(required = false) LocalDateTime dateTime) {
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        return stationService.isStationAvailable(stationId, dateTime);
    }
   /* @GetMapping("/{stationId}/bornes/{borneId}")
    public ResponseEntity<Object> isBorneExistInStation(@PathVariable String stationId, @PathVariable String borneId) {
        boolean isBorneExist = stationService.isBorneExistInStation(stationId, borneId);
        if (isBorneExist) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
   @GetMapping("/{stationId}/bornes/{borneId}")
   public boolean isBorneExistInStation(@PathVariable String stationId, @PathVariable String borneId) {
       return stationService.isBorneExistInStation(stationId, borneId);
   }
//    @GetMapping("/stations/nearby/{longitude}/{latitude}/{distance}")
//    public List<Stations> getStationsNearby(@PathVariable double longitude, @PathVariable double latitude, @PathVariable double distance) {
//        return stationService.findStationsNearby(longitude, latitude, distance);
//    }

}
