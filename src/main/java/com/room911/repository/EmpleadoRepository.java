package com.room911.repository;

import com.room911.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDocumento(String documento);
    boolean existsByCorreo(String correo);

    List<Empleado> findByActivoTrue();

}
