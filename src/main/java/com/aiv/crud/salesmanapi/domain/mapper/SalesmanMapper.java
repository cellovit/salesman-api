package com.aiv.crud.salesmanapi.domain.mapper;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.entity.Salesman;
import com.aiv.crud.salesmanapi.domain.request.SalesmanRequest;
import com.aiv.crud.salesmanapi.domain.response.SalesmanResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanResponse dtoToResponse(SalesmanDTO salesman);

    SalesmanDTO responseToDTO(SalesmanResponse response);

    SalesmanDTO requestToDTO(SalesmanRequest request);

    SalesmanDTO entityToDTO(Salesman entity);

    Salesman DTOToEntity(SalesmanDTO dto);

}
