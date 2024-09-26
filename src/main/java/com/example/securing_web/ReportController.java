package com.example.securing_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ReportController {

    private final static Logger LOGGER = Logger.getLogger(ReportController.class.getName());

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    @GetMapping("/report")
    public String getReportPage(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                @RequestParam(value = "idImpianto", required = false) Long idImpianto,
                                Model model) {
        List<Object[]> impressions;

        if (startDate != null && endDate != null) {
            if (idImpianto != null) {
                LOGGER.info("Fetching impressions for idImpianto " + idImpianto + " between " + startDate + " and " + endDate);
                impressions = segnalazioneRepository.findImpressionsBetweenDatesAndIdImpianto(startDate, endDate, idImpianto);
            } else {
                LOGGER.info("Fetching impressions between " + startDate + " and " + endDate);
                impressions = segnalazioneRepository.findImpressionsBetweenDates(startDate, endDate);
            }
        } else {
            LOGGER.info("Fetching all impressions");
            impressions = segnalazioneRepository.findAllImpressions();
        }

        model.addAttribute("impressions", impressions);
        return "report";
    }
}
