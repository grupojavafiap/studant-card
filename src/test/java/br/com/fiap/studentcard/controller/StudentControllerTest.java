package br.com.fiap.studentcard.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.studentcard.controllers.StudentController;
import br.com.fiap.studentcard.dto.StudentDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.services.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    StudentController.class
})
public class StudentControllerTest {

    private final String BASE_URL = "/student";
    
    private ObjectMapper mapper;

    @Autowired
    private StudentController studentController;
    
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                    .standaloneSetup(studentController)
                    .build();
    }

    @Test 
    public void shouldGetAllStudents() throws Exception 
    {
        ArrayList<Student> listStudent = new ArrayList<>();

        Student student = new Student();
        student.setAccountNumber("100-22");
        student.setCardNumber("123456789");
        student.setName("String Name");
        student.setId(1L);

        listStudent.add(student);

        when(studentService.findAll()).thenReturn(listStudent);

        mockMvc.perform(get(BASE_URL))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        
        verify(studentService, times(1)).findAll();

    }

    @Test
    public void shouldGetStudentId() throws Exception {
        Student student = new Student();
        student.setAccountNumber("100-22");
        student.setCardNumber("123456789");
        student.setName("String Name");
        student.setId(1L);

        when(studentService.findById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get(BASE_URL+"/1"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("String Name")))
            .andExpect(jsonPath("$.cardNumber", is("123456789")))
            .andExpect(jsonPath("$.accountNumber", is("100-22")));
        
        verify(studentService, times(1)).findById(1L);

    }

    @Test
    public void shouldSaveNewStudent() throws Exception {

        StudentDto studentDto = new StudentDto();
        studentDto.setAccountNumber("100-23");
        studentDto.setCardNumber("5544321980");
        studentDto.setName("String teste");


        Student student= new Student();
        student.setAccountNumber(studentDto.getAccountNumber());
        student.setCardNumber(studentDto.getCardNumber());
        student.setName(studentDto.getName());
        student.setId(1L);

        when(studentService.save(any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(post(BASE_URL)
                .content(mapper.writeValueAsString(studentDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("String teste")))
                .andExpect(jsonPath("$.cardNumber", is("5544321980")))
                .andExpect(jsonPath("$.accountNumber", is("100-23")));

        verify(studentService, times(1)).save(any(StudentDto.class));
    }

    @Test
    public void shouldDeleteStudentById() throws Exception {

        Student student = new Student();
        student.setId(1l);
        
        when(studentService.findById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().is(204));

        verify(studentService, times(1)).deleteById(1L);
    }


}
