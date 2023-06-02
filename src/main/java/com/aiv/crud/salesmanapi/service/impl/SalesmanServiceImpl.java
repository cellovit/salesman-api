package com.aiv.crud.salesmanapi.service.impl;

import com.aiv.crud.salesmanapi.domain.dto.SalesmanDTO;
import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.mapper.SalesmanMapper;
import com.aiv.crud.salesmanapi.domain.request.SalesmanCreateRequest;
import com.aiv.crud.salesmanapi.domain.request.SalesmanUpdateRequest;
import com.aiv.crud.salesmanapi.exception.EntityNotFoundException;
import com.aiv.crud.salesmanapi.exception.SalesmanSaveException;
import com.aiv.crud.salesmanapi.repository.SalesmanRepository;
import com.aiv.crud.salesmanapi.rest.client.BranchClient;
import com.aiv.crud.salesmanapi.service.SalesmanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
@Slf4j
public class SalesmanServiceImpl implements SalesmanService {

    Map<String, CompletableFuture<SalesmanDTO>> cfMap = new ConcurrentHashMap<>();

    private final SalesmanRepository repository;
    private final SalesmanMapper mapper;
    private final BranchClient branchClient;

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
    public SalesmanDTO findByRegistration(String registration) {
        return mapper.entityToDTO(repository.findByRegistration(registration)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar um vendedor com esta matrícula")));
    }

    @Override
    public CompletableFuture<SalesmanDTO> findByRegistrationAsync(String registration) {
        return cfMap.get(registration);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<SalesmanDTO> saveAsync(SalesmanCreateRequest request, String registrationString) {

        try {
            var cf1 = CompletableFuture.supplyAsync(() -> {
                var branch = branchClient.findBranchById(request.getBranchId());
                log.info(branch.toString());
                var entity = mapper.createRequestToEntity(request);
                entity.setRegistration(registrationString);
                var dto = mapper.entityToDTO(repository.save(entity));
                dto.setBranch(branch);
                return dto;
            });

            cfMap.put(registrationString, cf1);

            return cf1;

        } catch (CompletionException ex) {
            throw new SalesmanSaveException(ex.getMessage());
        }
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
    public SalesmanDTO updateAsync(long id, SalesmanUpdateRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            findById(id);
            var branch = branchClient.findBranchById(request.getBranchId());
            log.info(branch.toString());
            var toSave = mapper.updateRequestToEntity(request);
            toSave.setId(id);
            return mapper.entityToDTO(repository.save(toSave));
        }).join();
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


    @Override
    public String generateRegistrationString(HiringTypeEnum hiringType) {
        int registerNumber = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        return String.format("%d-%s", registerNumber, hiringType.getSuffix());
    }

}
