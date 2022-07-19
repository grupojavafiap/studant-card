package br.com.fiap.studentcard.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.fiap.studentcard.dto.TransactionDto;
import br.com.fiap.studentcard.models.Student;
import br.com.fiap.studentcard.models.Transaction;
import br.com.fiap.studentcard.repositories.StudentRepository;
import br.com.fiap.studentcard.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    final TransactionRepository transactionRepository;
    final StudentRepository studentRepository;
    Random random = new Random();

    public TransactionService(
        TransactionRepository transactionRepository,
        StudentRepository studentRepository) {
            this.transactionRepository = transactionRepository;
            this.studentRepository = studentRepository;
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(TransactionDto transactionDto) throws Exception {
        
        var transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        transaction.setCreated(LocalDateTime.now());

        Student student = studentRepository.getReferenceById(transactionDto.getStudentId());

        if(student == null) 
        {
            throw new Exception("Student not found");
        }

        transaction.setStudentTransaction(student);
        List<Transaction> transactionsList = new ArrayList<Transaction>();
        transactionsList.add(transaction);
        student.setTransactions(transactionsList);

        return transactionRepository.save(transaction);
    }
    

    public List<Transaction> findTransactionByStudentId(Long studentId) 
    {
        logger.info("[findTransactionByStudentId] - Consultando extrato do ESTUDANTE {}", studentId);

        return transactionRepository.findByStudentTransaction(new Student(studentId));
    }



    /**
     * Gera transação para cada estudante cadastrado na base 
     * de dados com valores randomicos. 
     */
    public void randomTransactions(int pageSize)
    { 
        var students = studentRepository.findAll();

        String stores[] = {"Americanas", "FIAP Coffee", "Nerdstore", "Google Cloud", "Starbucks", "Burger Dev"};

        students.stream().forEach(student -> saveAllTransaction(student, stores)); 
    }

    private  void saveAllTransaction(Student student, String stores[])
    {
        var amountTransaction = random.nextInt(10);
        var transactions = new ArrayList<Transaction>();

        for(int i = 0; i < amountTransaction; i++)
        {
            transactions.add(newRandomTransaction(student, stores));
        }

        transactionRepository.saveAll(transactions);
    }

    private Transaction newRandomTransaction(Student student, String stores[])
    {
        var transaction = new Transaction();
        var transactionValue = BigDecimal.valueOf(random.nextDouble() * 10);
        var storeId = random.nextInt(6);

        transaction.setTransactionValue(transactionValue);
        transaction.setStudentTransaction(student);
        transaction.setStore(stores[storeId]);
        transaction.setCreated(LocalDateTime.now());

        logger.info("Gerando transacao para {} no valor de {} ", student.getName(), transactionValue);

        return transaction;
    }
}
