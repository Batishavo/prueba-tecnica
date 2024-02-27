package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Persona;

public interface IPersonaDAO extends CrudRepository<Persona,Long> {
    
}
