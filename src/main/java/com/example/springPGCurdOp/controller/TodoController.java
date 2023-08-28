package com.example.springPGCurdOp.controller;

import com.example.springPGCurdOp.model.ErrorModel;
import com.example.springPGCurdOp.model.Todo;
import com.example.springPGCurdOp.repo.TodoRepo;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {
    @Autowired
    TodoRepo tdRepo;

    @PostMapping("/addtodo")
    public ResponseEntity<?> AddTodo(@RequestBody Todo todo) {
        try {
            tdRepo.save(todo);
            return new ResponseEntity<Todo>(todo, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            ErrorModel e = new ErrorModel("Something Gone Wrong");
            return new ResponseEntity<ErrorModel>(e, HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/getalltodo")
    public ResponseEntity<?> ListTodo() {
        try {
            List<Todo> allToDoList = tdRepo.findAll();
            return new ResponseEntity<List<Todo>>(allToDoList, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            ErrorModel e = new ErrorModel("Something Gone Wrong");
            return new ResponseEntity<ErrorModel>(e, HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/gettodobyid/{id}")
    public ResponseEntity<?> GetbyIdTodo(@PathVariable Long id) {
        try {
            Todo singleTodo = tdRepo.findById(id).get();
            return new ResponseEntity<>(singleTodo, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            ErrorModel e = new ErrorModel("Something Gone Wrong or Not Found");
            return new ResponseEntity<ErrorModel>(e, HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/deletetodo/{id}")
    public ResponseEntity<?> DeleteTodo(@PathVariable Long id) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            Todo delRec = tdRepo.findById(id).get();
            tdRepo.deleteById(id);
            map.put("deleted", true);
            map.put("data", delRec);
            return new ResponseEntity<Object>(map, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            ErrorModel e = new ErrorModel("Error Deleting data");
            return new ResponseEntity<ErrorModel>(e, HttpStatusCode.valueOf(400));
        }
    }


    @PutMapping("/completed/{id}")
    public ResponseEntity<?> updatetodo(@PathVariable Long id) {
        try {
            Todo updatetodo = tdRepo.findById(id).get();
            updatetodo.setCompleted(true);
            tdRepo.save(updatetodo);
            return new ResponseEntity<>(updatetodo, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            ErrorModel e = new ErrorModel("Error Deleting data");
            return new ResponseEntity<ErrorModel>(e, HttpStatusCode.valueOf(400));
        }
    }
}
