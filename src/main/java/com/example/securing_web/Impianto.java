package com.example.securing_web;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "impianti")
public class Impianto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idimpianto;
    private String descrizione;
    private double latitudine;
    private double longitudine;
    private String path;
    private String status;

    // Getters and Setters
    public Long getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(Long idimpianto) {
        this.idimpianto = idimpianto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}