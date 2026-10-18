package com.RH.API_RH.service;

import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    ColaboradorRepository colaboradorRepository;

    public String save (Colaborador colaborador){
        this.colaboradorRepository.save(colaborador);
        return "Colaborador salvo com sucesso!";
    }

    public Colaborador getByIdColaborador(long id){
        Colaborador colaborador = this.colaboradorRepository.findById(id).get();
        return colaborador;
    }
}
