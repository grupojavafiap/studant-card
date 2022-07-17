package br.com.fiap.studentcard.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void shouldExist()
    {
        Transaction transaction = new Transaction();
        transaction.setId(1l);
        transaction.setStore("storeName");
        transaction.setTransactionValue(BigDecimal.valueOf(1l));

        assertEquals(1l, transaction.getId());
        assertEquals("storeName", transaction.getStore());
        assertEquals(BigDecimal.valueOf(1l), transaction.getTransactionValue());

    }
    
}
