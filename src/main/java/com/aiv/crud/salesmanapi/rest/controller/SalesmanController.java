package com.aiv.crud.salesmanapi.rest.controller;

import com.aiv.crud.salesmanapi.domain.mapper.SalesmanMapper;
import com.aiv.crud.salesmanapi.domain.request.SalesmanCreateRequest;
import com.aiv.crud.salesmanapi.domain.request.SalesmanUpdateRequest;
import com.aiv.crud.salesmanapi.domain.response.SalesmanResponse;
import com.aiv.crud.salesmanapi.service.SalesmanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/v1/salesman")
@RequiredArgsConstructor
public class SalesmanController {

    private final SalesmanService salesmanService;
    private final SalesmanMapper salesmanMapper;

    @GetMapping(path = "list")
    public Page<SalesmanResponse> listPageable(Pageable pageable) {
        log.info(LocalDateTime.now().toString());
        return new PageImpl<>(salesmanService.listPageable(pageable)
                .stream()
                .map(salesmanMapper::dtoToResponse)
                .toList());
    }

    @GetMapping(path = "/{id}")
    public SalesmanResponse findById(@PathVariable long id) {
        log.info(LocalDateTime.now().toString());
        return salesmanMapper.dtoToResponse(salesmanService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<SalesmanResponse> save(@RequestBody @Valid SalesmanCreateRequest salesmanRequest) {
        log.info(salesmanRequest.toString());
        var dto = salesmanMapper.createRequestToDTO(salesmanRequest);
        return CompletableFuture.supplyAsync(() -> salesmanMapper.dtoToResponse(salesmanService.save(dto)));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CompletableFuture<SalesmanResponse> update(@PathVariable long id, @RequestBody @Valid SalesmanUpdateRequest salesmanRequest) {
        var dto = salesmanMapper.updateRequestToDTO(salesmanRequest);
        return CompletableFuture.supplyAsync(() -> salesmanMapper.dtoToResponse(salesmanService.update(id, dto)));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        salesmanService.delete(id);
    }

}
