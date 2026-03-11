package com.alexander.proyecto.services;

import com.alexander.proyecto.dto.PersonaRequest;
import com.alexander.proyecto.dto.PersonaResponse;
import com.alexander.proyecto.entities.Persona;
import com.alexander.proyecto.enums.Genero;
import com.alexander.proyecto.exceptions.RecursoNoEncontradoException;
import com.alexander.proyecto.mappers.PersonaMapper;
import com.alexander.proyecto.repositories.PersonaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponse> listar() {
        log.info("listado de todas las personas solicitado");
        return personaRepository.findAll()
                .stream()
                .map(personaMapper::entityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponse> obtenerPorNombre(String nombre) {
        log.info("listado de todas las personas solicitado");
        return personaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(personaMapper::entityToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponse obtenerPorId(Long id) {
        return personaMapper.entityToResponse(obtenerPersonaException(id));
    }

    @Override
    public PersonaResponse registrar(PersonaRequest request) {
        log.info("Registrando nueva persona: {}", request.nombre());

        Genero genero = Genero.obtenerPorAbreviacion(request.genero());

        String email = generarEmail(request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno());

        Persona persona = personaRepository.save(personaMapper.requestToEntity(request, genero, email));
        log.info("Nueva persona registrada: {}", persona.getNombre());

        return personaMapper.entityToResponse(persona);
    }

    @Override
    public PersonaResponse actualizar(PersonaRequest request, Long id) {
        Persona persona = obtenerPersonaException(id);

        log.info("Actualizando persona con id: {}", id);
        persona.setNombre(request.nombre());
        persona.setApellidoPaterno(request.apellidoPaterno());
        persona.setApellidoMaterno(request.apellidoMaterno());
        persona.setEdad(request.edad());
        persona.setTelefono(request.telefono());

        Genero genero = Genero.obtenerPorAbreviacion(request.genero());
        persona.setGenero(genero);

        String email = generarEmail(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno()
        );
        persona.setEmail(email);

        log.info("Person actualizada con id: {}", id);
        return personaMapper.entityToResponse(persona);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando persona con id: {}", id);
        personaRepository.delete(obtenerPersonaException(id));
        log.info("Persona eliminda con id: {}", id);
    }

    public Persona obtenerPersonaException(Long id){
        return personaRepository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("Persona no enontrada con el id: "+id));
    }

    private String obtenerPrimerosCaracteres(String texto, int cantidad){
        if (texto == null) return "";

        return texto.length() <= cantidad ? texto : texto.substring(0, cantidad);
    }

    private String generarEmail(String nombre, String apellidoPaterno, String apellidoMaterno){
        log.info("Generado emal...");
        return (
                obtenerPrimerosCaracteres(nombre, 5) +
                        obtenerPrimerosCaracteres(apellidoPaterno, 5)+
                        obtenerPrimerosCaracteres(apellidoMaterno, 5)+ "@ejemplo.com"
                ).toLowerCase();
    }

}
