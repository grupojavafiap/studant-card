package br.com.fiap.studentcard.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal transactionValue;


    @Column(nullable = false)
    private String store;

    @Column(nullable = false)
    private LocalDateTime created;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student studentTransaction;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getTransactionValue() {
        return this.transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Student getStudentTransaction() {
        return this.studentTransaction;
    }

    public void setStudentTransaction(Student student) {
        this.studentTransaction = student;
    } 

    public Transaction() {
    }

    public Transaction(long id, BigDecimal transactionValue, Student studentTransaction) {
        this.id = id;
        this.transactionValue = transactionValue;
        this.studentTransaction = studentTransaction;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", transactionValue='" + getTransactionValue() + "'" +
            ", studentTransactions='" + getStudentTransaction() + "'" +
            "}";
    }
    
}
