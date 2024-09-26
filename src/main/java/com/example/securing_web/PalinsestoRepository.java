package com.example.securing_web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PalinsestoRepository extends JpaRepository<Palinsesto, Long> {
    Palinsesto findByIdimpianto(Long idimpianto);
}
