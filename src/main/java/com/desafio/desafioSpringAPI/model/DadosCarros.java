package com.desafio.desafioSpringAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCarros(List<Dados> modelos) {
}
