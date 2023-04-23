package com.aiv.crud.salesmanapi.service;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesmanService {

    List<SalesmanDTO> listAll();

    Page<SalesmanDTO> listPageable(Pageable page);

    SalesmanDTO findById(long id);

    SalesmanDTO save(SalesmanDTO dto);

}
