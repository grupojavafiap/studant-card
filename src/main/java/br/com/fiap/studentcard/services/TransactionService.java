package br.com.fiap.studentcard.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    /**
     * Gera transação para cada estudante cadastrado na base 
     * de dados com valores randomicos. 
     */
    public void randomTransactions(int pageSize)
    { 
        Pageable limit = PageRequest.of(1,pageSize);
        
        var students = studentRepository.findAll(limit);

        String stores[] = {"Americanas", "FIAP Coffee", "Nerdstore", "Google Cloud", "Starbucks", "Burger Dev"};

        students.stream().forEach(student -> {
            var transaction = new Transaction();
            var transactionValue = BigDecimal.valueOf(random.nextDouble());
            var storeId = random.nextInt(5);

            transaction.setTransactionValue(transactionValue);
            transaction.setStudentTransaction(student);
            transaction.setStore(stores[storeId]);
            transaction.setCreated(LocalDateTime.now());

            logger.info("Gerando transacao para {} no valor de {} ", student.getName(), transactionValue);

            List<Transaction> transactionsList = new ArrayList<Transaction>();
            transactionsList.add(transaction);
            student.setTransactions(transactionsList);

            transactionRepository.save(transaction);
        }); 
    }
}
