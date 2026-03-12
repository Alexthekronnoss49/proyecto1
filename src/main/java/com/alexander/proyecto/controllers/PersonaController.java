package com.alexander.proyecto.controllers;

import com.alexander.proyecto.dto.PersonaRequest;
import com.alexander.proyecto.dto.PersonaResponse;
import com.alexander.proyecto.services.PersonaService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@AllArgsConstructor
@Validated
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaResponse>> listar(){
        return ResponseEntity.ok(personaService.listar());
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<PersonaResponse>> obtenerPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(personaService.obtenerPorNombre(nombre));
    }

    @GetMapping("/email")
    public ResponseEntity<List<PersonaResponse>> obtenerPorEmail(@RequestParam String email){
        return ResponseEntity.ok(personaService.obtenerPorEmail(email));
    }

    @GetMapping("/edad")
    public ResponseEntity<List<PersonaResponse>> obtenerPorRangoEdad(@RequestParam @Positive(message = "la edad mínima debe ser positiva") Short edadInicio,
                                                                     @RequestParam @Positive(message = "la edad máxima debe ser positiva") Short edadFin){
        return ResponseEntity.ok(personaService.obtenerPorRangoEdad(edadInicio, edadFin));
    }

    @GetMapping("/telefono")
    public ResponseEntity<List<PersonaResponse>> obtenerPorTelefono(@RequestParam String telefono){
        return ResponseEntity.ok(personaService.obtenerPorTelefono(telefono));
    }

    @GetMapping("/genero")
    public ResponseEntity<List<PersonaResponse>> obtenerPorGenero(@RequestParam Character genero){
        return ResponseEntity.ok(personaService.obtenerPorGenero(genero));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> obtenerPorId(@PathVariable
                                                            @Positive(message = "El id debe ser positivo") Long id){
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PersonaResponse> registrar(@Validated @RequestBody PersonaRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.registrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> actualizar(
            @Validated @RequestBody PersonaRequest request,
            @PathVariable @Positive(message = "el id debe ser positivo") Long id){
        return ResponseEntity.ok(personaService.actualizar(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaResponse> eliminar(@PathVariable
                                                        @Positive(message = "El id debe ser positivo") Long id){
       personaService.eliminar(id);
       return ResponseEntity.noContent().build();
    }
}
