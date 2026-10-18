package com.RH.API_RH.entity;

import jakarta.persistence.*;
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
    private String nomeCompleto;
    private int idade;
    private String sexo;
    private String cidade;
    private String salario;
    private String cargo;
    private LocalDate dataAdmissao;
    private LocalDate dataDesligamento;
    private LocalDate dataInicioFerio;
    private LocalDate dataFimFerias;
    private LocalDate dataInicioAfastamento;
    private LocalDate dataFimAfastamento;
    private String cargoConfianca;
    private LocalDateTime dataModificacao;
    private LocalDateTime dataDeCadastro;

    @PrePersist
    public void prePersist(){
        this.dataDeCadastro = LocalDateTime.now();
    }
}
