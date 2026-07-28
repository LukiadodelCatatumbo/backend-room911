package com.room911.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessAttemptDTO {
    private Long id;
    private LocalDateTime fechaAcceso;
    private Boolean exito;
    private String message;
    private Long empleadoId;
}
