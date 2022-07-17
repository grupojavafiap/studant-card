package br.com.fiap.studentcard.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import br.com.fiap.studentcard.models.Transaction;
import br.com.fiap.studentcard.repositories.StudentRepository;
import br.com.fiap.studentcard.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);


    @PersistenceContext
	private EntityManager entityManager;
    
    final TransactionRepository transactionRepository;
    final StudentRepository studentRepository;

    public CardService(TransactionRepository transactionRepository, StudentRepository studentRepository) 
    {
        this.transactionRepository = transactionRepository;
        this.studentRepository = studentRepository;
    }

    public List<Transaction> findTransactionByStudentId(Long studentId) 
    {
        logger.info("[findTransactionByStudentId] - Consultando extrato do ESTUDANTE {}", studentId);

        TypedQuery<Transaction> tp = entityManager.createQuery("SELECT t FROM Transaction t, Student s WHERE t.studentTransaction.id = s.id ", Transaction.class);
   
         return tp.getResultList();  
    }

}
