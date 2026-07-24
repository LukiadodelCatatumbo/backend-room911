package com.room911.service.interfaces;

import com.room911.dto.DepartamentoDTO;
import com.room911.dto.DepartamentoResponseDTO;

import java.util.List;

public interface DepartamentoService {
    DepartamentoResponseDTO guardar(DepartamentoDTO dto);
    List<DepartamentoResponseDTO> listar();
    DepartamentoResponseDTO buscarPorId(Long id);
    DepartamentoResponseDTO actualizar(Long id, DepartamentoDTO dto);

    void eliminar(Long id);
    
}
