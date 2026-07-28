package com.room911.service.interfaces;

import com.room911.dto.AccessAttemptDTO;

import java.util.List;

public interface AccessAttemptService {
    AccessAttemptDTO save(AccessAttemptDTO dto);
    List<AccessAttemptDTO> findAll();
    AccessAttemptDTO findById(Long id);
}