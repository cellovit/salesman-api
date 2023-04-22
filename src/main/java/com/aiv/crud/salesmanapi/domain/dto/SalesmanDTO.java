package com.aiv.crud.salesmanapi.domain.dto;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesmanDTO {

    private long id;
    private String registration; //matricula
    private String name;
    // validar formato data
    private LocalDate birthDate;
    private String cpf;
    private String cnpj;
    private String cpfCnpj;
    private String email;
    private HiringTypeEnum hiringType;
    private transient String branch;

}
