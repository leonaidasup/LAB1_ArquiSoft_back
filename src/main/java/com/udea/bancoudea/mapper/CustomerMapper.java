package com.udea.bancoudea.mapper;

import com.udea.bancoudea.DTO.CustomerDTO;
import com.udea.bancoudea.entity.Customer;
import org.mapstruct.Mapper;

// IMPORTANTE: Debes especificar componentModel = "spring"
// Esto le dice a MapStruct que la clase de implementación generada (CustomerMapperImpl)
// debe ser anotada con @Component o @Service para que Spring la reconozca como un Bean.
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
    // Método para convertir la entidad Customer a su DTO
    CustomerDTO toDto(Customer customer);

    // Método para convertir el DTO a la entidad Customer
    Customer toEntity(CustomerDTO customerDTO);
    
    // (Añade cualquier otro método de mapeo que necesites)
}