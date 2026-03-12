package com.alexander.proyecto.services;

import com.alexander.proyecto.dto.PersonaRequest;
import com.alexander.proyecto.dto.PersonaResponse;
import com.alexander.proyecto.entities.Persona;
import com.alexander.proyecto.enums.Genero;

import java.util.List;

public interface PersonaService {
    List<PersonaResponse> listar();

    List<PersonaResponse> obtenerPorNombre(String nombre);

    List<PersonaResponse> obtenerPorEmail(String email);

    List<PersonaResponse> obtenerPorRangoEdad(Short edadInicio, Short edadFin);

    List<PersonaResponse> obtenerPorTelefono(String telefono);

    List<PersonaResponse> obtenerPorGenero(Character genero);

    PersonaResponse obtenerPorId(Long id);

    PersonaResponse registrar(PersonaRequest request);

    PersonaResponse actualizar(PersonaRequest request, Long id);

    void eliminar(Long id);
}
