package com.RH.API_RH.controller;

import com.RH.API_RH.DTOs.colaboradorDTO;
import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.repository.ColaboradorRepository;
import com.RH.API_RH.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/colaborador/")
public class ControllerColaborador {
    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    ColaboradorRepository colaboradorRepository;

    @GetMapping("/testConnection")
    public ResponseEntity<String> testConnection(){
        return new ResponseEntity<>("Olá Mundo!", HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<colaboradorDTO> cadastrarColaborador(@RequestBody Colaborador colaborador, UriComponentsBuilder uriComponentsBuilder){
            var newColaborador = this.colaboradorRepository.save(colaborador);

            var uri = uriComponentsBuilder.path("api/colaborador/{id}").buildAndExpand(newColaborador.getId()).toUri();
            return ResponseEntity.created(uri).body(new colaboradorDTO(colaborador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<colaboradorDTO> findByIdColaborador(@PathVariable long id){
            Colaborador colaboradorId = this.colaboradorService.getByIdColaborador(id);
            return ResponseEntity.ok().body(new colaboradorDTO(colaboradorId));
    }

    @GetMapping("/allColaboradores")
    public ResponseEntity<List<Colaborador>> findAllColaboradores(){
        try {
            List<Colaborador> listAllEmployees = this.colaboradorService.findAllColaboradores();
            return new ResponseEntity<>(listAllEmployees, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<colaboradorDTO> atualizarColaborador(@PathVariable Long id, @RequestBody Map<String, Object> atributos){
            Colaborador atualizarColaborador = this.colaboradorService.atualizarColaborador(id, atributos);
            return ResponseEntity.accepted().body(new colaboradorDTO(atualizarColaborador));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<colaboradorDTO> atualizarColaboradorPut(@PathVariable Long id, @RequestBody Colaborador colaboradorDados){
            Colaborador atualizarColaborador = this.colaboradorService.atualizarColaboradorPut(id, colaboradorDados);
            return ResponseEntity.accepted().body(new colaboradorDTO(atualizarColaborador));
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarColaborador(@PathVariable Long id){
            colaboradorRepository.deleteById(id);
            return new ResponseEntity<>((HttpStatus) null, HttpStatus.NO_CONTENT);
    }
}
