package com.aiv.crud.salesmanapi.domain.mapper;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.entity.Salesman;
import com.aiv.crud.salesmanapi.domain.request.SalesmanCreateRequest;
import com.aiv.crud.salesmanapi.domain.request.SalesmanUpdateRequest;
import com.aiv.crud.salesmanapi.domain.response.SalesmanResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanResponse dtoToResponse(SalesmanDTO salesman);

    SalesmanDTO responseToDTO(SalesmanResponse response);

    SalesmanDTO createRequestToDTO(SalesmanCreateRequest request);

    SalesmanDTO updateRequestToDTO(SalesmanUpdateRequest request);

    SalesmanDTO entityToDTO(Salesman entity);

    Salesman DTOToEntity(SalesmanDTO dto);

}
