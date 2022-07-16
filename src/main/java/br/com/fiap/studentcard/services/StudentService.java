package br.com.fiap.studentcard.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.fiap.studentcard.dto.StudentDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.repositories.StudentRepository;

@Service
public class StudentService {

    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(StudentDto studentDto)
    {
        var student = new Student();

        BeanUtils.copyProperties(studentDto, student);
        return studentRepository.save(student);
    }

    public List<Student> findAll()
    {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id)
    {
        return studentRepository.findById(id);
    }

    public void deleleById(long id)
    {
        studentRepository.deleteById(id);
    }
    
}
