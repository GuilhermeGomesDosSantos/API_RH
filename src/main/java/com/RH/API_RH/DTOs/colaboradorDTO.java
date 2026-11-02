package com.RH.API_RH.DTOs;

import com.RH.API_RH.entity.Colaborador;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record colaboradorDTO(
        Long id,
        String nomeCompleto,
        Integer idade,
        String sexo,
        String cidade,
        String salario,
        String cargo,
        LocalDate dataAdmissao,
        LocalDate dataDesligamento,
        LocalDate dataInicioFerias,
        LocalDate dataFimFerias,
        LocalDate dataInicioAfastamento,
        LocalDate dataFimAfastamento,
        String cargoConfianca,
        LocalDateTime dataModificacao,
        LocalDateTime dataDeCadastro) {

    public colaboradorDTO(Colaborador colaborador){
        this(colaborador.getId(), colaborador.getNomeCompleto(), colaborador.getIdade(), colaborador.getSexo(), colaborador.getCidade(), colaborador.getSalario(), colaborador.getSexo(), colaborador.getDataAdmissao(), colaborador.getDataDesligamento(), colaborador.getDataInicioFerias(), colaborador.getDataFimFerias(), colaborador.getDataInicioAfastamento(), colaborador.getDataFimAfastamento(), colaborador.getCargoConfianca(), colaborador.getDataModificacao(), colaborador.getDataDeCadastro());
    }
}
