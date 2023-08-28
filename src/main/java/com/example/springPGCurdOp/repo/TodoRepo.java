package com.example.springPGCurdOp.repo;

import com.example.springPGCurdOp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TodoRepo extends JpaRepository<Todo, Long> {
}
