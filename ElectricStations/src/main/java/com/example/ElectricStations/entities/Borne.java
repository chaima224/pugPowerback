package com.example.ElectricStations.entities;

import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Disponibilite;
import com.example.ElectricStations.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Borne {
    @Id
    private String id;
    private String name;
    private float puissance;
    private float tempsCharge;
    private Mode mode;
    private Connecteur connecteur;
    private Image image;
    private Disponibilite disponibilite;
    private Date dateDisponibilite;



}
