package com.aiv.crud.salesmanapi.domain.entity;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Salesman {

    private String registration; //matricula
    private String name;
    private String cpf;
    private String cnpj;
    private String email;
    private HiringTypeEnum hiringType;
    @Transient
    private transient String branch;

    public Salesman(String num) {
        this.registration = String.format("%d-OUT1", ThreadLocalRandom.current().nextInt(10000000, 99999999));
    }

}
