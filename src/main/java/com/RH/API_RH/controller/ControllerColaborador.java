package com.RH.API_RH.controller;

import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/colaborador/")
public class ControllerColaborador {
    @Autowired
    ColaboradorService colaboradorService;

    @GetMapping("/testConnection")
    public ResponseEntity<String> testConnection(){
        return new ResponseEntity<>("Olá Mundo!", HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarColaborador(@RequestBody Colaborador colaborador){
        try {
            String cadastrarColaborador = this.colaboradorService.save(colaborador);
            return new ResponseEntity<>(cadastrarColaborador, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findByIdColaborador(@PathVariable long id){
            Colaborador colaboradorId = this.colaboradorService.getByIdColaborador(id);
            return new ResponseEntity<>("Colaborador Encontrado", HttpStatus.OK);
    }
}
