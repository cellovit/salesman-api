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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        return salesmanMapper.dtoToResponse(salesmanService.findById(id));
    }

    @GetMapping(path = "/matricula/{registration}")
    public SalesmanResponse findByRegistration(@PathVariable String registration) {
        return salesmanMapper.dtoToResponse(salesmanService.findByRegistrationAsync(registration).join());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody @Valid SalesmanCreateRequest salesmanRequest) {

        // retornar matricula gerada
        // criar endpoint para buscar pela matricula gerada

        String registrationString = salesmanService.generateRegistrationString(salesmanRequest.getHiringType());
        salesmanService.saveAsync(salesmanRequest, registrationString);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registrationString);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody @Valid SalesmanUpdateRequest salesmanRequest) {
        salesmanService.updateAsync(id, salesmanRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        salesmanService.delete(id);
    }

}
