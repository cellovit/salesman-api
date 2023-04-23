package com.aiv.crud.salesmanapi.domain.enums;

import lombok.Getter;

@Getter
public enum HiringTypeEnum {

    OUTSOURCING("OUT"), CLT("CLT"), LEGAL_PERSON("PJ");

    private final String suffix;

    HiringTypeEnum(String suffix) {
        this.suffix = suffix;
    };

}
