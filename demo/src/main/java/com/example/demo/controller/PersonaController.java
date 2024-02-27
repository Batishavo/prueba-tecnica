package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Persona;
import com.example.demo.services.classes.PersonaServices;
import com.example.demo.utils.MyToken;

@RestController
@RequestMapping("api/persona")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    PersonaServices personaIService;

    @Autowired
    MyToken myToken;

    @PostMapping
    public String postPersona(@RequestBody Persona persona, @RequestParam("token") String token) throws Exception {
        try {
            Persona personaAgregada = this.personaIService.guardar(persona);
            return "El registro de " + personaAgregada.getNombre() + ", proximamente tendra "
                    + (personaAgregada.getEdad() + 1) + " Años";
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping
    public List<Persona> getPersonas(@RequestParam("token") String token) throws Exception {
        try {
            myToken.verifyJws(token);
            return this.personaIService.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Persona> getPersona(@PathVariable(name = "id") Long id) throws Exception {
        try {
            Optional<Persona> persona = personaIService.getById(id);
            if (persona.isPresent()) {
                return ResponseEntity.ok(persona.get());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            throw e;
        }

    }

    @DeleteMapping("{id}")
    public String deletePersona(@PathVariable(name = "id") Long id) throws Exception {
        try {
            Optional<Persona> persona = personaIService.getById(id);
            if (persona.isPresent()) {
                personaIService.eliminar(id);
                return "Persona eliminada";
            }
            return "ID no Valido";
        } catch (Exception e) {
            throw e;
        }

    }

    @PatchMapping("{id}")
    public String sumarEdad(@PathVariable(name = "id") Long id) throws Exception {
        try {
            Optional<Persona> persona = personaIService.getById(id);
            if (persona.isPresent()) {
                personaIService.sumarEdad(persona.get());
                return "La persona tiene " + persona.get().getEdad() + 1;
            }
            return "ID no Valido";
        } catch (Exception e) {
            throw e;
        }

    }

    @PutMapping("{id}")
    public String putPersona(@PathVariable(name = "id") Long id, @RequestBody Persona persona) throws Exception {

        try {
            Optional<Persona> personaID = personaIService.getById(id);
            if (personaID.isPresent()) {
                personaIService.guardar(persona);
                return "Persona editada correctamente";
            }
            return "ID no Valido";
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("token")
    public String getToeken() {
        return this.myToken.createJason();
    }
}
