package com.room911.controller;

import com.room911.dto.DepartamentoDTO;
import com.room911.dto.DepartamentoResponseDTO;
import com.room911.service.interfaces.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    /**
     * Esto se usa para guardar un nuevo departamento
     */
    @PostMapping
    public ResponseEntity<DepartamentoResponseDTO> guardar(
            @Valid @RequestBody DepartamentoDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departamentoService.guardar(dto));
    }

    /**
     * Aqui es para listar todos los departamentos
     */
    @GetMapping
    public List<DepartamentoResponseDTO> listar() {
        return departamentoService.listar();
    }

    /**
     * Buscar un departamento por su id
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    /**
     * Actualizar formación de un departamento
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DepartamentoDTO dto) {

        return ResponseEntity.ok(departamentoService.actualizar(id, dto));
    }

    /**
     * Eliminación logica del departamento
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        departamentoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}