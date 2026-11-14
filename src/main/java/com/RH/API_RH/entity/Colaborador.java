package com.RH.API_RH.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "the nomeCompleto field is can't be null")
    private String nomeCompleto;
    @NotNull(message = "The idade field is can't be null")
    private Integer idade;
    @NotBlank(message = "The sexo field is can't be null")
    private String sexo;
    @NotBlank(message = "The cidade field is can't be null")
    private String cidade;
    @NotBlank(message = "The salario field is can't be null")
    private String salario;
    @NotBlank(message = "The cargo field is can't be null")
    private String cargo;
    private LocalDate dataAdmissao;
    private LocalDate dataDesligamento;
    private LocalDate dataInicioFerias;
    private LocalDate dataFimFerias;
    private LocalDate dataInicioAfastamento;
    private LocalDate dataFimAfastamento;
    private String cargoConfianca;
    private LocalDateTime dataModificacao;
    private LocalDateTime dataDeCadastro;

    @PrePersist
    public void prePersist(){
        this.dataDeCadastro = LocalDateTime.now();
        if(this.getCargoConfianca().isBlank()){
            this.setCargoConfianca("NAO");
        }
    }
}
