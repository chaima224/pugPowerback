package com.example.ElectricStations.entities;

import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Emplacement;
import com.example.ElectricStations.enums.Trajet;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@CrossOrigin

public class Stations {
    @Id
    private String id;
    private String name;
    @NonNull
    private double latitude;
    @NonNull
    private double longitude;
    @NonNull
    private double moyNote;
    @NonNull
    @DBRef
    private List<Borne> bornes;
    @NonNull
    private LocalDateTime ouverture;
    @NonNull
    private LocalDateTime fermeture;
    @NonNull
    private Emplacement emplacement ;
    @NonNull
    private Trajet trajet;
    @NonNull
    private float distance;

    public Stations(List<Borne> bornes) {
        this.bornes = bornes;
    }


}
