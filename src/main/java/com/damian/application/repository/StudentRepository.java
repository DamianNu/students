package com.damian.application.repository;

import com.damian.application.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    List<Student> findByLastName(String lastName, Pageable pageable);
//
//    List<Student> findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(String lastName, String firstName);
//
//    @Query("select s from Student s where  s.firstName = 'Marian'")
//    List<Student> findStudentsWithNameMarian();
}
