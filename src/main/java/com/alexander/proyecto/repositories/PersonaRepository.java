package com.alexander.proyecto.repositories;

import com.alexander.proyecto.entities.Persona;
import com.alexander.proyecto.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findByNombreContainingIgnoreCase(String nombre);

    List<Persona> findByEmailContainingIgnoreCase(String email);

    List<Persona> findByEdadBetween(Short edadInicio, Short edadFin);

    List<Persona> findByTelefono(String telefono);

    List<Persona> findByGenero(Genero genero);

    boolean existsByTelefono(String telefono);

    boolean existsByTelefonoAndIdNot(String telefono, Long Id);
}
