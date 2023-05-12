package com.example.ElectricStations.entities;

import com.example.ElectricStations.enums.Connecteur;
import com.example.ElectricStations.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor

public class CritereRecherche {
    private String id;
    private String depart;
    private String arrive;
    private double  latitude;
    private double longitude;
    private Connecteur connecteur;
    private float autonomie;
    private Date date;
    private float puissance;
    private  Mode mode;



}
