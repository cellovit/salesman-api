package com.aiv.crud.salesmanapi.domain.request;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.validators.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class SalesmanRequest {

    private long id;

    @JsonProperty("matricula")
    private String registration; //matricula

    @NotBlank
    @JsonProperty("nome")
    private String name;

    // validar formato data
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @JsonProperty("dataNascimento")
    private LocalDate birthDate;

    @CpfOrCnpj
    @NotBlank
    private String cpfCnpj;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @JsonProperty("tipoContrato")
    private HiringTypeEnum hiringType;

    private transient String branch;

}
