package br.com.fiap.studentcard.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.studentcard.dto.TransactionDto;
import br.com.fiap.studentcard.models.Transaction;
import br.com.fiap.studentcard.services.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/transaction")
@SwaggerDefinition(tags = {@Tag(name = "Transações", description = "Recursos das Transações do cartão de crédito")})
public class TransactionController {
    
    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "Consultar estudantes", notes = "Endpoint responsável por retornar uma lista de estudantes")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Retorna todos os estudantes cadastrados ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> findById(@PathVariable long id) 
    {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody @Valid TransactionDto transactionDto) throws Exception
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionDto));
    }    


    @PostMapping("random-transations")
    public ResponseEntity<Transaction> generateRandomTransations() throws Exception
    {
        transactionService.randomTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }  
}
