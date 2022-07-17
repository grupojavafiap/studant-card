package br.com.fiap.studentcard.dto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class TransactionDtoTest {

    @Test
    public void shouldExist() 
    {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setStore("store");
        transactionDto.setStudentId(1l);
        transactionDto.setTransactionValue(BigDecimal.valueOf(1l));

        assertEquals("store", transactionDto.getStore());
        assertEquals(1l, transactionDto.getStudentId());
        assertEquals(BigDecimal.valueOf(1l),transactionDto.getTransactionValue());
    }
    
}
