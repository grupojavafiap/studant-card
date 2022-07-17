package br.com.fiap.studentcard.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.studentcard.controllers.TransactionController;
import br.com.fiap.studentcard.dto.TransactionDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.models.Transaction;
import br.com.fiap.studentcard.services.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    TransactionController.class
})
public class TransactionControllerTest {

    private final String BASE_URL = "/transaction";

    private ObjectMapper mapper;

    @Autowired
    private TransactionController transactionController;
    
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();

        mockMvc = MockMvcBuilders
                    .standaloneSetup(transactionController)
                    .build();
    }

    @Test
    public void shouldGetTransactionById() throws Exception {
        
        Student student = new Student();
        student.setAccountNumber("100-22");
        student.setCardNumber("123456789");
        student.setName("String Name");
        student.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setCreated(LocalDateTime.now());
        transaction.setId(1l);
        transaction.setStore("Americanas");
        transaction.setStudentTransaction(student);

        when(transactionService.findById(1L)).thenReturn(Optional.of(transaction));

        mockMvc.perform(get(BASE_URL+"/1"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.store", is("Americanas")));
        
        verify(transactionService, times(1)).findById(1L);

    }    

    @Test
    public void shouldSaveNewTransaction() throws Exception {

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setStore("Americanas");
        transactionDto.setStudentId(1L);

        Student student= new Student();
        student.setAccountNumber("100-10");
        student.setCardNumber("12345678");
        student.setName("Student Name");
        student.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setCreated(LocalDateTime.now());
        transaction.setId(1);
        transaction.setStore(transactionDto.getStore());
        transaction.setStudentTransaction(student);
        transaction.setTransactionValue(BigDecimal.ONE);


        when(transactionService.save(any(TransactionDto.class))).thenReturn(transaction);

        mockMvc.perform(post(BASE_URL)
                .content(mapper.writeValueAsString(transactionDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.store", is("Americanas")));

        verify(transactionService, times(1)).save(any(TransactionDto.class));
    }
}
