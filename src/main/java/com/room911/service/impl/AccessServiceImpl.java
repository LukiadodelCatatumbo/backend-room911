package com.room911.service.impl;

import com.room911.dto.AccessRequestDTO;
import com.room911.dto.AccessResponseDTO;
import com.room911.entity.AccessAttempt;
import com.room911.entity.Empleado;
import com.room911.repository.AccessAttemptRepository;
import com.room911.repository.EmpleadoRepository;
import com.room911.service.interfaces.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final EmpleadoRepository empleadoRepository;
    private final AccessAttemptRepository accessAttemptRepository;

    @Override
    public AccessResponseDTO validarAcceso(AccessRequestDTO dto) {

        Optional<Empleado> empleadoOpt =
                empleadoRepository.findByDocumento(dto.getDocumento());

        if (empleadoOpt.isEmpty()) {

            AccessAttempt intento = AccessAttempt.builder()
                    .fechaAcceso(LocalDateTime.now())
                    .exito(false)
                    .message("Empleado no registrado")
                    .empleado(null)
                    .build();

            accessAttemptRepository.save(intento);

            return AccessResponseDTO.builder()
                    .permitido(false)
                    .mensaje("Empleado no registrado")
                    .nombreEmpleado(null)
                    .build();
        }

        Empleado empleado = empleadoOpt.get();

        return AccessResponseDTO.builder()
                .permitido(true)
                .mensaje("Empleado encontrado")
                .nombreEmpleado(
                        empleado.getNombre() + " " + empleado.getApellido())
                .build();
    }
}