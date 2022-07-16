package br.com.fiap.studentcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.studentcard.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}