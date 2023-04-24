package com.aiv.crud.salesmanapi.service;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesmanService {

    Page<SalesmanDTO> listPageable(Pageable page);

    SalesmanDTO findById(long id);

    SalesmanDTO save(SalesmanDTO dto);

    void delete(long id);

    SalesmanDTO update(long id, SalesmanDTO dto);

}
