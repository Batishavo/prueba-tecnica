package com.example.demo.services.interfaces;

import java.util.Optional;

import com.example.demo.models.Persona;

import java.util.List;

public interface  IService <T> {
    List<T> listar();
    Optional<T> getById(Long id);
    Persona guardar(T t);
    void eliminar(Long id);
}
