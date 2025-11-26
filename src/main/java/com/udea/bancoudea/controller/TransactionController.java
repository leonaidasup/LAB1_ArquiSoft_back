package com.udea.bancoudea.controller;

import com.udea.bancoudea.DTO.TransactionDTO;
import com.udea.bancoudea.DTO.TransferRequestDTO;
import com.udea.bancoudea.service.TransactionService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
@Tag(name = "TransfersAccounts", description = "Operacion relacionada a las transacciones")
public class TransactionController {
    private final TransactionService transactioFacade;
    public TransactionController(TransactionService transactioFacade) {this.transactioFacade = transactioFacade;}

    //Obtener todas las transacciones
    @GetMapping("/{accountNumber}")
    @Operation(
            summary = "Obtener todas las transacciones por número de cuenta",
            description = "Este Endpoint permite encontrar todas las transacciones realizadas por número de cuenta"
    )
    public ResponseEntity<List<TransactionDTO>> getTransactionsForAccount(@PathVariable String accountNumber) {
        List<TransactionDTO> transactions = transactioFacade.getTransactionsForAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    //Realizar tranferencia entre cuentas
    @PostMapping("/transfer")
    @Operation(
            summary = "Realizar transferencia entre cuentas",
            description = "Este Endpoint permite realizar una transferencia entre dos cuentas bancarias"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transferencia realizada con éxito"),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 404, message = "Cuenta no encontrada"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public ResponseEntity<TransactionDTO> transferMoney(@RequestBody TransferRequestDTO transferRequest) {
        try{
            //Llamar al servicio para realizar transferencia
            TransactionDTO transaction = transactioFacade.transferMoney(new TransactionDTO(
                    null,
                    transferRequest.getSenderAccountNumber(),
                    transferRequest.getReceiverAccountNumber(),
                    transferRequest.getAmount(),
                    null
            ));
            return ResponseEntity.ok(transaction);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
