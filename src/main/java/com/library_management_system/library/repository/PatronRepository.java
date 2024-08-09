package com.library_management_system.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library_management_system.library.entity.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}
