package com.app.repository;

import com.app.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswereRepository extends JpaRepository<Answer,Long> {
}
