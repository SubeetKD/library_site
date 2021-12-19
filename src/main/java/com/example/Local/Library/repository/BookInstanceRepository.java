package com.example.Local.Library.repository;

import com.example.Local.Library.entity.BookInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookInstanceRepository extends JpaRepository<BookInstanceEntity, Long> {
    List<BookInstanceEntity> findByUserIdAndBookId(Long userId, Long bookId);
    List<BookInstanceEntity> findByUserIdAndBookIdAndStatus(Long userId, Long bookId, String status);
}
