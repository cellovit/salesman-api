package com.aiv.crud.salesmanapi.rest.controller;

import com.aiv.crud.salesmanapi.domain.mapper.SalesmanMapper;
import com.aiv.crud.salesmanapi.domain.request.SalesmanRequest;
import com.aiv.crud.salesmanapi.domain.response.SalesmanResponse;
import com.aiv.crud.salesmanapi.service.SalesmanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("salesman")
@RequiredArgsConstructor
public class SalesmanController {

    private final SalesmanService salesmanService;
    private final SalesmanMapper salesmanMapper;

    @GetMapping(path = "list")
    public List<SalesmanResponse> list() {
        log.info(LocalDateTime.now().toString());
        return salesmanService.listAll()
                .stream()
                .map(salesmanMapper::dtoToResponse)
                .toList();
    }

    @GetMapping(path = "listPageable")
    public Page<SalesmanResponse> listPageable(Pageable pageable) {
        log.info(LocalDateTime.now().toString());
        return new PageImpl<>(salesmanService.listPageable(pageable)
                .stream()
                .map(salesmanMapper::dtoToResponse)
                .toList());
    }

    @PostMapping
    public CompletableFuture<SalesmanResponse> save(@RequestBody @Valid SalesmanRequest salesmanRequest) {
        log.info(salesmanRequest.toString());
        var dto = salesmanMapper.requestToDTO(salesmanRequest);
        return CompletableFuture.supplyAsync(() -> salesmanMapper.dtoToResponse(salesmanService.save(dto)));
    }

}
