package br.com.fiap.studentcard.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentDtoTest {

    @Test
    public void shouldExist() 
    {
        StudentDto studentDto = new StudentDto();
        studentDto.setAccountNumber("100-1");
        studentDto.setCardNumber("12345678");
        studentDto.setName("Name");

        assertEquals("100-1", studentDto.getAccountNumber());
        assertEquals("12345678", studentDto.getCardNumber());
        assertEquals("Name", studentDto.getName());
    }    
}
