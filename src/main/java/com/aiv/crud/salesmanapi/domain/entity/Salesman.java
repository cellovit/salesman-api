package com.aiv.crud.salesmanapi.domain.entity;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.validators.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Entity
@Table(name = "salesman")
public class Salesman {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String registration; //matricula

    @NotBlank
    @Column(nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @CPF
    @Column(unique = true)
    private String cpf;

    @CNPJ
    @Column(unique = true)
    private String cnpj;

    @CpfOrCnpj
    @NotBlank
    @Column(unique = true)
    private String cpfCnpj;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private HiringTypeEnum hiringType;

    @Transient
    @Column(nullable = false)
    private transient String branch;

    public Salesman(String num) {
        this.registration = String.format("%d-OUT1", ThreadLocalRandom.current().nextInt(10000000, 99999999));
    }

}
