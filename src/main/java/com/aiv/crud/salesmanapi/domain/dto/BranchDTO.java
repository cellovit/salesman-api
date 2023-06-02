package com.aiv.crud.salesmanapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private long id;

    @JsonProperty("nome")
    private String name;

    private String cnpj;

    @JsonProperty("cidade")
    private String city;

    private String uf;

    @JsonProperty("tipo")
    private String type;

    @JsonProperty("ativo")
    private boolean active;

    @JsonProperty("dataRegistro")
    private LocalDate registerDate;

    @JsonProperty("ultimaAtualizacao")
    private LocalDate lastUpdate;

}
