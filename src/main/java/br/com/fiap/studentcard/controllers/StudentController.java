package br.com.fiap.studentcard.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.studentcard.dto.StudentDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.services.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/student")
//@Tag(name = "Alunos", description = "Recursos da API de Alunos/Clientes")
public class StudentController {
    
    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation(value = "Consulta estudantes", notes = "Endpoint responsável por retornar uma lista de estudantes")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Retorna todos os estudantes cadastrados ")
    })
    @GetMapping
    public ResponseEntity<List<Student>> findAll() 
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @ApiOperation(value = "Consulta um estudante a partir do ID", notes = "Endpoint responsável por consultar estudante através do ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Retorna informações do estudante")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable long id) 
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id) 
    {
        studentService.deleleById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody @Valid StudentDto studentDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentDto));
    }

}
