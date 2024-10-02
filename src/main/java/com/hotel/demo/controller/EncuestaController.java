package com.hotel.demo.controller;

import org.springframework.ui.Model;
import com.hotel.demo.entity.Encuesta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.hotel.demo.repository.EncuestaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
public class EncuestaController {
    private EncuestaRepository encuestaRepository;

    public EncuestaController(EncuestaRepository repository){
        this.encuestaRepository = repository;
    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("encuesta", new Encuesta());
        return "encuesta-form.html";
    }

    @PostMapping ("/")
    public String insertarEncuesta(EncuestaRepository repository, Encuesta encuesta){
        repository.save(encuesta);
        return "encuesta-form.html";
    }

    @GetMapping("/admin")
    public String findAll(Model model){
            List<Encuesta> encuestas = this.encuestaRepository.findAll(); //Método para obtener todas las encuestas
            model.addAttribute("encuestas", encuestas);
            return "encuesta-admin.html"; // nombre del archivo HTML sin la extensión
    }

    }


