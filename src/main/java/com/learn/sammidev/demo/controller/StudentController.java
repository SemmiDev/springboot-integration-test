package com.learn.sammidev.demo.controller;

import com.learn.sammidev.demo.entity.Student;
import com.learn.sammidev.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable("id") Integer id){
        return studentRepo.findById(id).orElseThrow();
    }
}
