package com.library_management_system.library.controller;

import com.library_management_system.library.dto.PatronRequestDto;
import com.library_management_system.library.entity.Patron;
import com.library_management_system.library.service.PatronService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return ResponseEntity.ok(patronService.getAllPatrons().join());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id).join();
        if (patron == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patron);
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody PatronRequestDto patronRequestDto) {
        Patron savedPatron = patronService.createPatron(patronRequestDto).join();
        return ResponseEntity.status(201).body(savedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id,
            @Valid @RequestBody PatronRequestDto patronDetails) {
        Patron updatedPatron = patronService.updatePatron(id, patronDetails).join();
        if (updatedPatron == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedPatron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        if (!patronService.deletePatron(id).join())
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}