package com.RH.API_RH;

import com.RH.API_RH.controller.ControllerColaborador;
import com.RH.API_RH.entity.Colaborador;
import com.RH.API_RH.repository.ColaboradorRepository;
import com.RH.API_RH.service.ColaboradorService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiRhApplicationTests {

	@Autowired
	ColaboradorService colaboradorService;

	@Autowired
	ControllerColaborador controllerColaborador;

	@Autowired
	ColaboradorRepository colaboradorRepository;

	@Test
	@Description("O teste tem que encontrar o ID informado")
	void cenario01_findById() {
		Long id = 1L;

		Colaborador retorno = this.colaboradorService.getByIdColaborador(id);

		System.out.println("ID Encontrado: " + retorno.getId());
		assertEquals(id, retorno.getId());
	}

	@Test
	@Description("Retorna é esperado erro ao não encontrar o ID informado")
	void cenario02_findById(){
		Long id = 10L;

//		ApiErrorResponseNoSuchElementException exception = assertThrows(ApiErrorResponseNoSuchElementException.class, ()-> this.colaboradorService.getByIdColaborador(id));
//		assertEquals("No value present", exception.getMessage());
	assertThrows(NoSuchElementException.class, () -> this.colaboradorService.getByIdColaborador(id));
	}

	@Test
	@Description("Criando colaborador")
	void cenario03_save(){
		Colaborador colaborador = new Colaborador();
		colaborador.setNomeCompleto("Guilherme Santos Teste");
		colaborador.setSexo("Masculino");
		colaborador.setCidade("São Paulo");
		colaborador.setCargo("Desenvolvedor");
		colaborador.setIdade(20);
		colaborador.setSalario("3000");
		colaborador.setCargoConfianca("SIM");

		String retorno = this.colaboradorService.save(colaborador);

		assertEquals("Colaborador salvo com sucesso!", retorno);
	}

	@Test
	void cenario04_atualizar_colaborador_patch(){
		Map<String, Object> atributos = new HashMap<>();

		atributos.put("nomeCompleto", "GUILHERME");
		atributos.put("cargo", "Dev Sr");
		atributos.put("salario", "9000");

		this.colaboradorService.atualizarColaborador(3L, atributos);
	}

	@Test
	void cenario05_atualizar_colaborador_put(){
		Long id = 3L;
		Colaborador dadosNovosColaborador = new Colaborador();
		dadosNovosColaborador.setNomeCompleto("Gui teste2");
		dadosNovosColaborador.setCargo("Dev Jr III");

		this.colaboradorService.atualizarColaboradorPut(id, dadosNovosColaborador);
	}

	@Test
	void cenario06_deletar_colaborador(){
		this.controllerColaborador.deletarColaborador(5L);

		assertFalse(this.colaboradorRepository.existsById(5L));
	}
}
