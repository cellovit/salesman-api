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
public class SalesmanCreateRequest {

    @NotBlank(message = "O campo 'nome' não pode estar vazio")
    @JsonProperty("nome")
    private String name;

    // validar formato data
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "O campo 'dataNascimento' não pode estar vazio")
    @JsonProperty("dataNascimento")
    private LocalDate birthDate;

    @CpfOrCnpj(message = "O campo 'cpfCnpj' deve ser preenchido com um cpf ou cnpj válido")
    @NotBlank(message = "O campo 'cpfCnpj' não pode estar vazio")
    private String cpfCnpj;

    @Email(message = "O campo 'email' deve ser preenchido com um valor válido")
    @NotBlank(message = "O campo 'email' não pode estar vazio")
    private String email;

    @NotNull(message = "O campo 'tipoContrato' não pode estar vazio")
    @JsonProperty("tipoContrato")
    private HiringTypeEnum hiringType;

//    @NotNull(message = "O campo 'idFilial' não pode estar vazio")
//    @JsonProperty("idFilial")
//    private String branchId;

}
