package br.com.fiap.studentcard.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.studentcard.models.Transaction;
import br.com.fiap.studentcard.services.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/card")
@SwaggerDefinition(tags = {@Tag(name = "Cartão", description = "Recursos dos Cartão de crédito")})
public class CardController {
    
    final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "Extrato de lançamentos", notes = "Endpoint responsável por retornar uma lista de transações de um estudante.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Retorna a lista de transações de um estudante")
    })
    @GetMapping("/statement/{studentId}")
    public ResponseEntity<List<Transaction>> findById(@PathVariable long studentId) 
    {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.findTransactionByStudentId(studentId));
    }

}
