package com.aiv.crud.salesmanapi.service;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.request.SalesmanCreateRequest;
import com.aiv.crud.salesmanapi.domain.request.SalesmanUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.concurrent.CompletableFuture;

public interface SalesmanService {

    Page<SalesmanDTO> listPageable(Pageable page);

    SalesmanDTO findById(long id);

    SalesmanDTO findByRegistration(String registration);

    CompletableFuture<SalesmanDTO> findByRegistrationAsync(String registration);

    CompletableFuture<SalesmanDTO> saveAsync(SalesmanCreateRequest request, String registrationString);

    SalesmanDTO save(SalesmanDTO dto);

    void delete(long id);

    SalesmanDTO updateAsync(long id, SalesmanUpdateRequest request);

    SalesmanDTO update(long id, SalesmanDTO dto);

    String generateRegistrationString(HiringTypeEnum hiringType);

}
