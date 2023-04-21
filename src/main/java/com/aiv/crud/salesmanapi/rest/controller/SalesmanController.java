package com.aiv.crud.salesmanapi.rest.controller;

import com.aiv.crud.salesmanapi.domain.entity.Salesman;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("salesman")
public class SalesmanController {

    @GetMapping(path = "list")
    public List<Salesman> list(){
        log.info(LocalDateTime.now().toString());
        return List.of(new Salesman("1"), new Salesman("2"));
    }

}
