package com.aiv.crud.salesmanapi.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponse {

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
