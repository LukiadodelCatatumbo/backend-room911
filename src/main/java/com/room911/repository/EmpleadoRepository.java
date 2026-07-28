package com.room911.repository;

import com.room911.entity.Departamento;
import com.room911.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDocumento(String documento);
    Optional<Empleado> findByDocumento(String documento);
    boolean existsByCorreo(String correo);

    List<Empleado> findByActivoTrue();

    List<Empleado> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    List<Empleado> findByApellidoContainingIgnoreCaseAndActivoTrue(String apellido);

    List<Empleado> findByDepartamentoIdAndActivoTrue(Long departamentoId);
}
