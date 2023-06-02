package com.aiv.crud.salesmanapi.domain.response;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesmanResponse {

    private long id;

    @JsonProperty("matricula")
    private String registration; //matricula

    @JsonProperty("nome")
    private String name;

    @JsonProperty("dataNascimento")
    private LocalDate birthDate;

    private String cpfCnpj;

    private String email;

    @JsonProperty("tipoContrato")
    private HiringTypeEnum hiringType;

    @JsonProperty("filial")
    private BranchResponse branch;

}
