package br.com.fiap.studentcard.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransactionDto {

    @NotNull
    private BigDecimal transactionValue;

    @NotNull
    private long studentId;

    @NotNull
    private String store;

    public BigDecimal getTransactionValue() {
        return this.transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }


    
    
}
