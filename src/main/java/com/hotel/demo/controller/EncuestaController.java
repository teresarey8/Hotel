package com.hotel.demo.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.hotel.demo.entity.Encuesta;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.hotel.demo.repository.EncuestaRepository;
import org.thymeleaf.model.IModel;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class EncuestaController {
    private EncuestaRepository encuestaRepository;
    //mostrar el formulario de encuesta
    public EncuestaController(EncuestaRepository repository){
        this.encuestaRepository = repository;
    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("encuesta", new Encuesta());
        return "encuesta-form";
    }
    //guarda la informacion en la base de datos
    @PostMapping ("/")
    public String insertarEncuesta(@Valid Encuesta encuesta, BindingResult bindingResult){
        //Si ha habido errores de validación volvemos a mostrar el formulario
        if (bindingResult.hasErrors()){
            return "encuesta-form";
        }
        //Si no ha habido errores de validación insertamos los datos en la BD
        encuestaRepository.save(encuesta);
        System.out.println("Encuesta guardada: "+encuesta);
        //aqui te deberia mandar a otro sitio(cuando has finalizado el formulario y esta correcto)
        return "redirect:/admin";
    }

    //vista de administrador que muestra el listado d las encuestas
    @GetMapping("/admin")
    public String findAll(Model model){
            List<Encuesta> encuestas = encuestaRepository.findAll(); //Metodo para obtener todas las encuestas
            model.addAttribute("encuestas", encuestas);
            return "encuesta-admin"; // nombre del archivo HTML sin la extensión
    }

    @PostMapping("/admin/del/{id}")
    public String borrarEncuesta(@PathVariable Long id) {
        encuestaRepository.deleteById(id);
        return "redirect:/admin"; // Redirigir a la lista de encuestas después de borrar
    }

    // Ver detalles de la encuesta
    @GetMapping("/admin/ver/{id}")
    public String verEncuesta(@PathVariable Long id, Model model) {
        Optional<Encuesta> optionalEncuesta = encuestaRepository.findById(id);

        if (optionalEncuesta.isPresent()) {
            Encuesta encuesta = optionalEncuesta.get();
            model.addAttribute("encuesta", encuesta);
            return "encuesta-ver";
        } else {
            // Manejo del caso en que la encuesta no se encuentra
            model.addAttribute("errorMessage", "Encuesta no encontrada");
            return "error"; // Puedes redirigir a una página de error o a la lista de encuestas
        }
    }

    // Mostrar el formulario de edición
    @GetMapping("/admin/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Encuesta encuesta = encuestaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada con id: " + id));
        model.addAttribute("encuesta", encuesta);
        return "encuesta-edit"; // Crea una plantilla para editar la encuesta
    }

    // Procesar la actualización de la encuesta
    @PostMapping("/admin/editar/{id}")
    public String actualizarEncuesta(@PathVariable Long id, @Valid Encuesta encuesta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "encuesta-edit"; // Volver al formulario si hay errores
        }
        encuesta.setId(id); // Asegúrate de que el ID esté configurado
        encuestaRepository.save(encuesta);
        return "redirect:/admin"; // Redirigir a la lista de encuestas después de actualizar
    }




}


