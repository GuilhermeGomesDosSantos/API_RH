package com.RH.API_RH.service;

import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public List<Colaborador> findAllColaboradores(){
        List<Colaborador> listaColaboradores = this.colaboradorRepository.findAll();
        return listaColaboradores;
    }

    public Colaborador atualizarColaborador(Long id, Map<String, Object> atributos){
        try {
            Colaborador colaborador = this.colaboradorRepository.findById(id).get();
            if (colaborador != null) {
                if (atributos.containsKey("idade")) colaborador.setIdade((Integer) atributos.get("idade"));
                if (atributos.containsKey("sexo")) colaborador.setSexo((String) atributos.get("sexo"));
                if (atributos.containsKey("cidade")) colaborador.setCidade((String) atributos.get("cidade"));
                if (atributos.containsKey("salario")) colaborador.setSalario((String) atributos.get("salario"));
                if (atributos.containsKey("cargo")) colaborador.setCargo((String) atributos.get("cargo"));
                if (atributos.containsKey("cargoConfianca")) colaborador.setCargoConfianca((String) atributos.get("cargoConfianca"));
                if (atributos.containsKey("dataAdmissao")) colaborador.setDataAdmissao((LocalDate) atributos.get("dataAdmissao"));
                colaborador.setDataModificacao(LocalDateTime.now());
            }
            return this.colaboradorRepository.save(colaborador);
        } catch (Exception e){
            return null;
        }
    }
}
