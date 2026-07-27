package com.room911.service.impl;

import com.room911.dto.EmpleadoDTO;
import com.room911.dto.EmpleadoResponseDTO;
import com.room911.entity.Departamento;
import com.room911.entity.Empleado;
import com.room911.mapper.EmpleadoMapper;
import com.room911.repository.DepartamentoRepository;
import com.room911.repository.EmpleadoRepository;
import com.room911.service.interfaces.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public EmpleadoResponseDTO guardar(EmpleadoDTO dto) {
        if (empleadoRepository.existsByDocumento(dto.getDocumento())){
            throw new RuntimeException("El documento ya esta registrado");
        }

        if (empleadoRepository.existsByCorreo(dto.getCorreo())){
            throw new RuntimeException("El correo ya esta registrado");
        }

        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Empleado empleado = Empleado.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .documento(dto.getDocumento())
                .correo(dto.getCorreo())
                .cargo(dto.getCargo())
                .departamento(departamento)
                .activo(true)
                .fechaCreacion(LocalDateTime.now())
                .build();
        Empleado guardado = empleadoRepository.save(empleado);
        return EmpleadoMapper.toDTO(guardado);
    }

    @Override
    public List<EmpleadoResponseDTO> listar() {
        return empleadoRepository.findByActivoTrue()
                .stream()
                .map(EmpleadoMapper::toDTO)
                .toList();
    }

    @Override
    public EmpleadoResponseDTO buscarPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (!empleado.getActivo()){
            throw new RuntimeException("El empleado se encuentra inactivo");
        }

        return EmpleadoMapper.toDTO(empleado);
    }

    @Override
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoDTO dto) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (!empleado.getDocumento().equals(dto.getDocumento())
        && empleadoRepository.existsByDocumento(dto.getDocumento())){
            throw new RuntimeException("El documento ya esta registrado");
        }

        if (!empleado.getCorreo().equals(dto.getCorreo())
        && empleadoRepository.existsByCorreo(dto.getCorreo())){
            throw new RuntimeException("El correo ya esta registrado");
        }

        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setDocumento(dto.getDocumento());
        empleado.setCorreo(dto.getCorreo());
        empleado.setCargo(dto.getCargo());
        empleado.setDepartamento(departamento);
        empleado.setFechaActualizacion(LocalDateTime.now());

        Empleado actualizado = empleadoRepository.save(empleado);
        return EmpleadoMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {

        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        /**
         * Se usa la eliminacion logica para conservar el historial del empleado
         * y asi evitar problemas de integridad referencial con otras entidades
         * que pueden estar relacionadas
         */
        empleado.setActivo(false);
        empleado.setFechaActualizacion(LocalDateTime.now());
        empleadoRepository.save(empleado);
    }

}