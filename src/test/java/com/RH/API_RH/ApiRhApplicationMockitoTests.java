package com.RH.API_RH;

import com.RH.API_RH.controller.ControllerColaborador;
import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.repository.ColaboradorRepository;
import com.RH.API_RH.service.ColaboradorService;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiRhApplicationMockitoTests {

    @MockitoBean
    ColaboradorRepository colaboradorRepository;

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    ControllerColaborador controllerColaborador;

    Colaborador colaborador = new Colaborador();

    List<Colaborador> listaColaborador;

    @BeforeEach
    void beforeEach(){
//        Colaborador colaborador = new Colaborador();
        colaborador.setNomeCompleto("Guilherme");
        colaborador.setSalario("3000");
        colaborador.setIdade(20);
        colaborador.setId(1L);

        listaColaborador = new ArrayList<>();
        this.listaColaborador.add(colaborador);
    }

    @Test
    @Description("GET ID Especifico")
    void cenario01_get_id_especifico(){
        //Quando alguma classe chamar o findById do repository, ele irá retornar um colaborador já previamente instanciado no @beforeEach
        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));

        //O service irá chamar o repository e o repository já tem o objeto colaborador que foi retornado no when
        Colaborador resultado = colaboradorService.getByIdColaborador(1L);

        System.out.println("ID Colaborador " + resultado.getId());

        assertEquals(1L, resultado.getId());
    }

    @Test
    @Description("Retornar 200 OK para ler todos os colaboradores")
    void cenario02_get_retorna_todos_colaboradores(){
        when(colaboradorRepository.findAll()).thenReturn(listaColaborador);
        ResponseEntity<List<Colaborador>> resultado = this.controllerColaborador.findAllColaboradores();
        System.out.println(resultado.getBody());
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    void cenario03_post_criar_colaborador(){
        when(colaboradorRepository.save(colaborador)).thenReturn(colaborador);

        String retorno = this.colaboradorService.save(colaborador);

        assertEquals("Colaborador salvo com sucesso!", retorno);

    }
}
