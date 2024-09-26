package com.example.securing_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class ImpiantoController {

    private final static Logger LOGGER = Logger.getLogger(ImpiantoController.class.getName());

    @Autowired
    private ImpiantoRepository impiantoRepository;

    @Autowired
    private PalinsestoRepository palinsestoRepository;

    @GetMapping("/impianti")
    public String listImpianti(Model model) {
        LOGGER.info("Entering listImpianti method");
        model.addAttribute("impianti", impiantoRepository.findAll());
        return "impianti";
    }

    @GetMapping("/modificaImpianto")
    public String modificaImpianto(@RequestParam Long idimpianto, Model model) {
        LOGGER.info("Entering modificaImpianto method with idimpianto: " + idimpianto);
        Impianto impianto = impiantoRepository.findById(idimpianto).orElse(null);
        model.addAttribute("impianto", impianto);
        return "modificaImpianto";
    }

    @PostMapping("/updateImpianto")
    public String updateImpianto(@RequestParam Long idimpianto,
                                 @RequestParam String descrizione,
                                 @RequestParam double latitudine,
                                 @RequestParam double longitudine,
                                 @RequestParam String path,
                                 @RequestParam String status) {
        LOGGER.info("Entering updateImpianto method with idimpianto: " + idimpianto);
        Impianto impianto = impiantoRepository.findById(idimpianto).orElse(null);
        if (impianto != null) {
            impianto.setDescrizione(descrizione);
            impianto.setLatitudine(latitudine);
            impianto.setLongitudine(longitudine);
            impianto.setPath(path);
            impianto.setStatus(status);
            impiantoRepository.save(impianto);

            Palinsesto palinsesto = palinsestoRepository.findByIdimpianto(idimpianto);
            if (palinsesto != null) {
                palinsesto.setPath(path);
                palinsestoRepository.save(palinsesto);
            } else {
                palinsesto = new Palinsesto();
                palinsesto.setIdimpianto(idimpianto);
                palinsesto.setPath(path);
                palinsestoRepository.save(palinsesto);
            }

            LOGGER.info("Impianto updated successfully: " + impianto);
        }
        return "redirect:/impianti";
    }


    @GetMapping("/aggiungiImpianto")
    public String aggiungiImpiantoForm(Model model) {
        LOGGER.info("Entering aggiungiImpiantoForm method");
        model.addAttribute("impianto", new Impianto());
        return "aggiungiImpianto";
    }

    @PostMapping("/aggiungiImpianto")
    public String aggiungiImpianto(@RequestParam String descrizione,
                                   @RequestParam double latitudine,
                                   @RequestParam double longitudine,
                                   @RequestParam String path,
                                   @RequestParam String status) {
        LOGGER.info("Entering aggiungiImpianto method with descrizione: " + descrizione);
        try {
            Impianto impianto = new Impianto();
            impianto.setDescrizione(descrizione);
            impianto.setLatitudine(latitudine);
            impianto.setLongitudine(longitudine);
            impianto.setPath(path);
            impianto.setStatus(status);
            impiantoRepository.save(impianto);

            Long idimpianto = impianto.getIdimpianto();

            Palinsesto palinsesto = new Palinsesto();
            palinsesto.setIdimpianto(idimpianto);
            palinsesto.setPath(path);
            palinsestoRepository.save(palinsesto);

            LOGGER.info("Impianto aggiunto con successo: " + impianto);
            LOGGER.info("Palinsesto aggiunto con successo: " + palinsesto);
        } catch (Exception e) {
            LOGGER.severe("Errore durante l'aggiunta dell'impianto: " + e.getMessage());
            throw e;
        }
        return "redirect:/impianti";
    }

    @PostMapping("/cancellaImpianto")
    public String cancellaImpianto(@RequestParam Long idimpianto) {
        try {
            impiantoRepository.deleteById(idimpianto);
            LOGGER.info("Impianto cancellato con successo: ID=" + idimpianto);
        } catch (Exception e) {
            LOGGER.severe("Errore durante la cancellazione dell'impianto: " + e.getMessage());
            throw e;
        }
        return "redirect:/impianti";
    }

    @GetMapping("/visualizzaPalinsesto")
    public String visualizzaPalinsesto(@RequestParam Long idimpianto, Model model) {
        LOGGER.info("Entering visualizzaPalinsesto method with idimpianto: " + idimpianto);

        Impianto impianto = impiantoRepository.findById(idimpianto).orElse(null);
        if (impianto == null) {
            LOGGER.warning("Impianto non trovato per idimpianto: " + idimpianto);
            return "impiantoNonAttivo";
        }

        if ("disattivo".equalsIgnoreCase(impianto.getStatus())) {
            LOGGER.warning("Impianto con idimpianto: " + idimpianto + " è disattivo.");
            return "impiantoNonAttivo";
        }

        Palinsesto palinsesto = palinsestoRepository.findByIdimpianto(idimpianto);
        if (palinsesto != null) {
            String pathWithExtension = "/xml/" + palinsesto.getPath() + ".xml";
            LOGGER.info("Path with extension: " + pathWithExtension);
            model.addAttribute("path", pathWithExtension);
            return "visualizzaPalinsesto";
        }

        LOGGER.warning("Palinsesto non trovato per idimpianto: " + idimpianto);
        return "impiantoNonAttivo";
    }

}
