package com.room911.controller;

import com.room911.dto.EmpleadoDTO;
import com.room911.dto.EmpleadoResponseDTO;
import com.room911.service.interfaces.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    /**
     *Registrar un nuevo empleado
     */

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> guardar(
            @Valid @RequestBody EmpleadoDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoService.guardar(dto));
    }

    /**
     * Listar los empleados activos
     */

    @GetMapping
    public List<EmpleadoResponseDTO> listar(){
        return empleadoService.listar();
    }

    /**
     * Buscar empleado por su id
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> buscarPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(empleadoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualiza(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoDTO dto){
        return ResponseEntity.ok(empleadoService.actualizar(id, dto));
    }

    /**
     * Eliminar un empleado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
