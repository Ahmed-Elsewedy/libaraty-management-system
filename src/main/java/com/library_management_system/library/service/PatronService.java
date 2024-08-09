package com.library_management_system.library.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.library_management_system.library.dto.PatronRequestDto;
import com.library_management_system.library.entity.Patron;

import com.library_management_system.library.repository.PatronRepository;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Async
    public CompletableFuture<List<Patron>> getAllPatrons() {
        return CompletableFuture.completedFuture(patronRepository.findAll());
    }

    @Async
    public CompletableFuture<Patron> getPatronById(Long id) {
        return CompletableFuture
                .completedFuture(patronRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Patron not found")));
    }

    @Async
    public CompletableFuture<Patron> createPatron(PatronRequestDto patronRequestDto) {
        Patron patron = new Patron();
        patron.setName(patronRequestDto.getName());
        patron.setEmail(patronRequestDto.getEmail());
        patron.setPhoneNumber(patronRequestDto.getPhoneNumber());
        return CompletableFuture.completedFuture(patronRepository.save(patron));
    }

    @Async
    public CompletableFuture<Patron> updatePatron(Long id, PatronRequestDto patronDetails) {
        return CompletableFuture.completedFuture(
                patronRepository.findById(id).map(patron -> {
                    patron.setName(patronDetails.getName());
                    patron.setEmail(patronDetails.getEmail());
                    patron.setPhoneNumber(patronDetails.getPhoneNumber());
                    return patronRepository.save(patron);
                }).orElseThrow(() -> new ResourceNotFoundException("Patron not found")));
    }

    @Async
    public CompletableFuture<Boolean> deletePatron(Long id) {
        return CompletableFuture.completedFuture(
                patronRepository.findById(id).map(patron -> {
                    patronRepository.delete(patron);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Patron not found")));

    }
}
