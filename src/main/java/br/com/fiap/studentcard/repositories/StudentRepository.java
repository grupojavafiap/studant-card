package br.com.fiap.studentcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.studentcard.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {}
