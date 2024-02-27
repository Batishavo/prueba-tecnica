package com.example.demo.services.classes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.IPersonaDAO;
import com.example.demo.models.Persona;
import com.example.demo.services.interfaces.IService;

@Component
public class PersonaServices implements IService<Persona> {

    @Autowired
    private IPersonaDAO personaDAO;

    @Override
    public List<Persona> listar() {
        return (List<Persona>) personaDAO.findAll();
    }

    @Override
    public Optional<Persona> getById(Long id) {
        return personaDAO.findById(id);
    }

    @Override
    public Persona guardar(Persona t) {
        return this.personaDAO.save(t);
    }

    @Override
    public void eliminar(Long id) {
       this.personaDAO.deleteById(id);
    }

    public void sumarEdad(Persona persona) {
        Integer edad = persona.getEdad();
        persona.setEdad(edad + 1);
        this.guardar(persona);
    }

}
