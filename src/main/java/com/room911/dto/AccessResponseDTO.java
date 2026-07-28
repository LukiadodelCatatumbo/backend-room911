package com.room911.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessResponseDTO {

    private Boolean permitido;
    private String mensaje;
    private String nombreEmpleado;
}
