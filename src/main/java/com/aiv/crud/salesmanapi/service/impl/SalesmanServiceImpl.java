package com.aiv.crud.salesmanapi.service.impl;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.mapper.SalesmanMapper;
import com.aiv.crud.salesmanapi.exception.EntityNotFoundException;
import com.aiv.crud.salesmanapi.repository.SalesmanRepository;
import com.aiv.crud.salesmanapi.service.SalesmanService;
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
    public Page<SalesmanDTO> listPageable(Pageable page) {
        List<SalesmanDTO> list = repository.findAll(page)
                .map(mapper::entityToDTO)
                .toList();
        return new PageImpl<>(list);
    }

    @Override
    public SalesmanDTO findById(long id) {
        return mapper.entityToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar um vendedor com este ID")));
    }

    @Override
    @Transactional
    public SalesmanDTO save(SalesmanDTO dto) {
        var entity = mapper.DTOToEntity(dto);
        entity.setRegistration(generateRegistrationString(dto.getHiringType()));
        return mapper.entityToDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public SalesmanDTO update(long id, SalesmanDTO dto) {
        findById(id);
        var toSave = mapper.DTOToEntity(dto);
        toSave.setId(id);
        return mapper.entityToDTO(repository.save(toSave));
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    private String generateRegistrationString(HiringTypeEnum hiringType ) {
        int registerNumber = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        return String.format("%d-%s", registerNumber, hiringType.getSuffix());
    }

}
