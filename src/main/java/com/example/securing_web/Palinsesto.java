package com.example.securing_web;

import jakarta.persistence.*;

@Entity
@Table(name = "palinsesti")

public class Palinsesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpalinsesto;
    private Long idimpianto;
    private String path;

    public Long getIdpalinsesti() {
        return idpalinsesto;
    }

    public void setIdpalinsesti(Long idpalinsesti) {
        this.idpalinsesto = idpalinsesti;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(Long idimpianto) {
        this.idimpianto = idimpianto;
    }
}

