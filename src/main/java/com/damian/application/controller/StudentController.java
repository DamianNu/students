package com.damian.application.controller;

import com.damian.application.model.Student;
import com.damian.application.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody @Valid Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    return ResponseEntity.ok().body(studentRepository.save(studentFromDb));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(studentFromDb -> {
                    if (!StringUtils.isEmpty(student.getFirstName())) {
                        studentFromDb.setFirstName(student.getFirstName());
                    }
                    if (!StringUtils.isEmpty(student.getLastName())) {
                        studentFromDb.setLastName(student.getLastName());
                    }
                    if (!StringUtils.isEmpty(student.getEmail())) {
                        studentFromDb.setEmail(student.getEmail());
                    }
                    return ResponseEntity.ok().body(studentRepository.save(studentFromDb));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @GetMapping("/lastname")
//    public List<Student> findStudent(@RequestParam String lastName, @RequestParam int numberOfPage) {
//        Pageable pageable = PageRequest.of(numberOfPage, 2, Sort.by("firstName"));
//        return studentRepository.findByLastName(lastName, pageable);
//    }
//
//    @GetMapping("/find")
//    public List<Student> findStudent2(@RequestParam String lastName, @RequestParam String firstName) {
//        return studentRepository.findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(lastName, firstName);
//    }
//
//    @GetMapping("/marian")
//    public List<Student> findStudent3() {
//        return studentRepository.findStudentsWithNameMarian();
//    }

}
