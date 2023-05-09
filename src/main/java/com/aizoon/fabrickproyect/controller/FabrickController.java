package com.aizoon.fabrickproyect.controller;

import com.aizoon.fabrickproyect.entity.DTO.BonificoDTO;
import com.aizoon.fabrickproyect.entity.DTO.ResponseDTO;
import com.aizoon.fabrickproyect.service.IFabrickService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fabrick")
public class FabrickController {

    private final IFabrickService fabrickService;

    public FabrickController(IFabrickService fabrickService) {
        this.fabrickService = fabrickService;
    }

    @GetMapping("/saldo")
    public ResponseEntity<?> letturaSaldo(@RequestParam(defaultValue = "14537780") Long accountId){

        ResponseDTO response = fabrickService.getSaldo(accountId);

        return Objects.equals(response.getStatus(), "OK") ?
                new ResponseEntity<>(response.getPayload(), HttpStatus.OK) :
                new ResponseEntity<>(response.getErrors(),HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/bonifico")
    public ResponseEntity<?> bonifico(@RequestParam(defaultValue = "14537780") Long accountId,
                                      @RequestParam String receiverName,
                                      @RequestParam String description,
                                      @RequestParam String currency,
                                      @RequestParam(defaultValue = "0") Long amount,
                                      @RequestParam String executionDate){

        BonificoDTO bonifico = new BonificoDTO(receiverName,"GB94BARC10201530093459",
                description,amount,currency,executionDate);

        ResponseDTO response = fabrickService.bonifico(accountId,bonifico);

        return Objects.equals(response.getStatus(), "OK") ?
                new ResponseEntity<>(response.getPayload(), HttpStatus.OK) :
                new ResponseEntity<>(response.getErrors(),HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/transazioni")
    public ResponseEntity<?> transazioni(@RequestParam(defaultValue = "14537780") Long accountId,
                                         @RequestParam String fromAccountingDate,
                                         @RequestParam String toAccountingDate){

        ResponseDTO response = fabrickService.transazioni(accountId,fromAccountingDate,toAccountingDate);

        return Objects.equals(response.getStatus(), "OK") ?
                new ResponseEntity<>(response.getPayload(), HttpStatus.OK) :
                new ResponseEntity<>(response.getErrors(),HttpStatus.BAD_REQUEST);
    }

}
