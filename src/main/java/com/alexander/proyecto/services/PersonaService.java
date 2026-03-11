package com.alexander.proyecto.services;

import com.alexander.proyecto.dto.PersonaRequest;
import com.alexander.proyecto.dto.PersonaResponse;

import java.util.List;

public interface PersonaService {
    List<PersonaResponse> listar();

    List<PersonaResponse> obtenerPorNombre(String nombre);

    PersonaResponse obtenerPorId(Long id);

    PersonaResponse registrar(PersonaRequest request);

    PersonaResponse actualizar(PersonaRequest request, Long id);

    void eliminar(Long id);
}
