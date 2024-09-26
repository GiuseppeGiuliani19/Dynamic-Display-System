package com.example.securing_web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {

    @Query("SELECT s.idImpianto, COUNT(s) FROM Segnalazione s WHERE s.timestamp BETWEEN :startDate AND :endDate GROUP BY s.idImpianto")
    List<Object[]> findImpressionsBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT s.idImpianto, COUNT(s) FROM Segnalazione s WHERE s.timestamp BETWEEN :startDate AND :endDate AND s.idImpianto = :idImpianto GROUP BY s.idImpianto")
    List<Object[]> findImpressionsBetweenDatesAndIdImpianto(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("idImpianto") Long idImpianto);

    @Query("SELECT s.idImpianto, COUNT(s) FROM Segnalazione s GROUP BY s.idImpianto")
    List<Object[]> findAllImpressions();
}
