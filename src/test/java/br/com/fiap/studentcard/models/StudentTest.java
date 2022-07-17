package br.com.fiap.studentcard.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    public void shouldExist()
    {
        Student student = new Student();
        student.setAccountNumber("100-1");
        student.setCardNumber("123");
        student.setId(1l);
        student.setName("name");

        assertEquals("100-1", student.getAccountNumber());
        assertEquals("123", student.getCardNumber());
        assertEquals(1l, student.getId());
        assertEquals("name", student.getName());
    }
    
}
