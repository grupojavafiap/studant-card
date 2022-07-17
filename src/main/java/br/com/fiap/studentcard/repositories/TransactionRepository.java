package br.com.fiap.studentcard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.studentcard.dto.StatementDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //@Query(name =  "SELECT T.CREATED, T.STORE, T.TRANSACTION_VALUE, S.NAME FROM TRANSACTION T INNER JOIN STUDENT S  ON T.STUDENT_ID = S.ID " 
    //+ "WHERE T.STUDENT_ID = ?1", nativeQuery = true)

    //@Query(name =  "SELECT T FROM TRANSACTION T INNER JOIN STUDENT S  ON T.STUDENT_ID = S.ID " 
    //+ "WHERE T.STUDENT_ID = ?1", nativeQuery = true)

    //@Query(name =  "SELECT t FROM TRANSACTION t WHERE t.studentTransaction = ?1", nativeQuery = true)
    //public List<Transaction> findByStudentTransactionByStudantId(Long student);
}