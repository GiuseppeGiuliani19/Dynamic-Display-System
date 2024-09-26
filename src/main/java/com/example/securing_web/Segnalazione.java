package com.example.securing_web;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "segnalazioni")
public class Segnalazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsegnalazione")
    private Long idSegnalazione;

    @Column(name = "idimpianto", nullable = false)
    private Long idImpianto;

    @Column(name = "idpalinsesto", nullable = false)
    private Long idPalinsesto;

    @Column(name = "idcartellone", nullable = false)
    private Long idCartellone;

    @Column(name = "durata", nullable = false)
    private int durata;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Getters and setters

    public Long getIdSegnalazione() {
        return idSegnalazione;
    }

    public void setIdSegnalazione(Long idSegnalazione) {
        this.idSegnalazione = idSegnalazione;
    }

    public Long getIdImpianto() {
        return idImpianto;
    }

    public void setIdImpianto(Long idImpianto) {
        this.idImpianto = idImpianto;
    }

    public Long getIdPalinsesto() {
        return idPalinsesto;
    }

    public void setIdPalinsesto(Long idPalinsesto) {
        this.idPalinsesto = idPalinsesto;
    }

    public Long getIdCartellone() {
        return idCartellone;
    }

    public void setIdCartellone(Long idCartellone) {
        this.idCartellone = idCartellone;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}