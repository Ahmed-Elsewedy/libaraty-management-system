package com.library_management_system.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library_management_system.library.entity.BorrowingRecord;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookIdAndPatronId(@Param("bookId") Long bookId, @Param("patronId") Long patronId);

    List<BorrowingRecord> findByReturnDateIsNull();

    List<BorrowingRecord> findByPatronId(Long patronId);
}
