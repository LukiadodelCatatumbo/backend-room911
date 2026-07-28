package com.room911.repository;

import com.room911.entity.AccessAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccessAttemptRepository extends JpaRepository<AccessAttempt, Long> {

    List<AccessAttempt> findByEmpleadoId(Long empleadoId);

    List<AccessAttempt> findByEmpleadoIdAndFechaAccesoBetween(
            Long empleadoId,
            LocalDateTime inicio,
            LocalDateTime fin
    );
}