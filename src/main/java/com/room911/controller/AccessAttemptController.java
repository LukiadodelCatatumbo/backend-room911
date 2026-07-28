package com.room911.controller;

import com.room911.dto.AccessAttemptDTO;
import com.room911.service.interfaces.AccessAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intento-acceso")
@RequiredArgsConstructor
public class AccessAttemptController {
    private final AccessAttemptService accessAttemptService;

    @PostMapping
    public ResponseEntity<AccessAttemptDTO> save(@RequestBody AccessAttemptDTO dto){
        return ResponseEntity.ok(accessAttemptService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<AccessAttemptDTO>> findAll(){
        return ResponseEntity.ok(accessAttemptService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessAttemptDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(accessAttemptService.findById(id));
    }
}
