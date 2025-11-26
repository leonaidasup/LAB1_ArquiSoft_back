package com.udea.bancoudea.controller;

import com.udea.bancoudea.DTO.CustomerDTO;
import com.udea.bancoudea.service.CustomerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
@Tag(name ="Customers", description = "Operación relacionada a los clientes del banco")
public class CustomerController {
    private final CustomerService customerFacade;

    public CustomerController(CustomerService customerFacade) {
        this.customerFacade = customerFacade;
    }

    //Obtener todos los cliente
    @GetMapping
    @Operation(
            summary = "Obtener todas los clientes del banco",
            description = "Este Endpoint permite encontrar todos los usuarios del bancoudea"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios consultados con éxito"),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 404, message = "Cuenta no encontrada"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerFacade.getAllCustomer());
    }

    @Operation(
            summary = "Obtener usuario por Id del banco",
            description = "Este Endpoint permite encontrar usuario por Id del bancoudea"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario consultado con éxito"),
            @ApiResponse(code = 400, message = "Solicitud inválida"),
            @ApiResponse(code = 404, message = "Cuenta no encontrada"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })

    //Obtener un cliente por un ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerFacade.getCustomerById(id));
    }

//Crear un nuevo cliente
@Operation(
        summary = "Crear usuario del banco",
        description = "Este Endpoint permite crear un usuario del bancoudea"
)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuario creado con éxito"),
        @ApiResponse(code = 400, message = "Solicitud inválida"),
        @ApiResponse(code = 404, message = "Cuenta no encontrada"),
        @ApiResponse(code = 500, message = "Error interno del servidor")
})
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        if(customerDTO.getBalance() == null) {
            throw new IllegalArgumentException("Balance cannot be null");
        }
        return ResponseEntity.ok(customerFacade.createCustomer(customerDTO));
    }
}
