package com.room911.mapper;

import com.room911.dto.AccessAttemptDTO;
import com.room911.entity.AccessAttempt;

public class AccessAttemptMapper {
    public static AccessAttemptDTO toDTO(AccessAttempt accessAttempt){
        return AccessAttemptDTO.builder()
                .id(accessAttempt.getId())
                .fechaAcceso(accessAttempt.getFechaAcceso())
                .exito(accessAttempt.getExito())
                .message(accessAttempt.getMessage())
                .empleadoId(accessAttempt.getEmpleado() != null ? accessAttempt.getEmpleado().getId() : null)
                .build();
    }
}
