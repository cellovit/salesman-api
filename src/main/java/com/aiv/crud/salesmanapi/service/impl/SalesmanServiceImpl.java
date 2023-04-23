package com.aiv.crud.salesmanapi.service.impl;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.mapper.SalesmanMapper;
import com.aiv.crud.salesmanapi.repository.SalesmanRepository;
import com.aiv.crud.salesmanapi.service.SalesmanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
@Slf4j
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository repository;
    private final SalesmanMapper mapper;

    @Override
    public List<SalesmanDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public Page<SalesmanDTO> listPageable(Pageable page) {
        List<SalesmanDTO> list = repository.findAll(page)
                .map(mapper::entityToDTO)
                .toList();
        return new PageImpl<>(list);
    }

    @Override
    public SalesmanDTO findById(long id) {
        return mapper.entityToDTO(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar um vendedor com este ID")));
    }

    @Override
    @Transactional
    public SalesmanDTO save(@Valid SalesmanDTO dto) {
        var entity = mapper.DTOToEntity(dto);
        int registerNumber = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        String registrationString = String.format("%d-%s", registerNumber, dto.getHiringType().getSuffix());
        entity.setRegistration(registrationString);
        return mapper.entityToDTO(repository.save(entity));
    }

}
