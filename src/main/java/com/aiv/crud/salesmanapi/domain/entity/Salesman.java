package com.aiv.crud.salesmanapi.domain.entity;

import com.aiv.crud.salesmanapi.domain.enums.HiringTypeEnum;
import com.aiv.crud.salesmanapi.domain.validators.CpfOrCnpj;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    @CpfOrCnpj
    @NotBlank
    @Column(unique = true, nullable = false)
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
    private long branchId;

}
