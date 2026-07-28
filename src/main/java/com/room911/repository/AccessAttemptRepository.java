package com.room911.repository;

import com.room911.entity.AccessAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessAttemptRepository  extends JpaRepository<AccessAttempt, Long> {

}
