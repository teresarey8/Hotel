package com.hotel.demo.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.hotel.demo.entity.Encuesta;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.hotel.demo.repository.EncuestaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class EncuestaController {
    private EncuestaRepository encuestaRepository;

    // Constructor
    public EncuestaController(EncuestaRepository repository) {
        this.encuestaRepository = repository;
    }

    // Mostrar el formulario de encuesta
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("encuesta", new Encuesta());
        return "encuesta-form";
    }

    // Guarda la información en la base de datos
    @PostMapping("/")
    public String insertarEncuesta(@Valid Encuesta encuesta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "encuesta-form";
        }
        encuestaRepository.save(encuesta);
        System.out.println("Encuesta guardada: " + encuesta);
        return "redirect:/admin";
    }

    // Vista de administrador que muestra el listado de las encuestas
    @GetMapping("/admin")
    public String findAll(@RequestParam(required = false) String nsgeneral, Model model) {
        List<Encuesta> encuestas;

        if (nsgeneral != null && !nsgeneral.isEmpty()) {
            encuestas = encuestaRepository.findByNsgeneral(nsgeneral);
        } else {
            encuestas = encuestaRepository.findAll();
        }

        // Calcular estadísticass
        int totalEncuestas = encuestas.size();
        double promedioEdad = encuestas.stream()
                .filter(encuesta -> encuesta.getEdad() != null) // Filtra encuestas con edad no nula, porque si no me da error
                .mapToLong(Encuesta::getEdad)//map devuelve un numero tipo long
                .average()
                .orElse(0);
        //array con los niveles de satisfaccion
        String[] nivelesSatisfaccion = {
                "Muy insatisfecho", "Insatisfecho", "Neutral", "Satisfecho", "Muy satisfecho"
        };
        // array del numero de niveles que hay
        double[] distribucionSatisfaccion = new double[nivelesSatisfaccion.length];

        // Contar los niveles de satisfacción
        for (Encuesta encuesta : encuestas) {
            String nivel = encuesta.getNsgeneral();
            for (int i = 0; i < nivelesSatisfaccion.length; i++) {
                if (nivelesSatisfaccion[i].equals(nivel)) {
                    distribucionSatisfaccion[i]++;
                }
            }
        }

        // Calcular porcentajes, podria haber metido un if, pero asi esta mejor.
        for (int i = 0; i < distribucionSatisfaccion.length; i++) {
            distribucionSatisfaccion[i] = totalEncuestas > 0
                    ? (distribucionSatisfaccion[i] * 100.0 / totalEncuestas) : 0;
        }

        // Agregar los atributos al modelo
        model.addAttribute("encuestas", encuestas);
        //con esto conseguimos que se vean los niveles de satisfaccion en las estadistica
        model.addAttribute("nsgeneral", nivelesSatisfaccion);
        model.addAttribute("totalEncuestas", totalEncuestas);
        model.addAttribute("promedioEdad", promedioEdad);
        model.addAttribute("distribucionSatisfaccion", distribucionSatisfaccion);

        //Uso de un Map: Se utiliza un HashMap para contar las ocurrencias de cada nivel de satisfacción sin necesidad de un switch.
        //Cálculo de Distribución: El cálculo de la distribución de niveles de satisfacción se realiza a partir del Map, obteniendo el conteo de cada nivel y calculando el porcentaje correspondiente.

        return "encuesta-admin"; // Nombre del archivo HTML sin la extensión por dios
    }

    @PostMapping("/admin/borrar/{id}")
    public String borrarEncuesta(@PathVariable("id") Long id) {
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
