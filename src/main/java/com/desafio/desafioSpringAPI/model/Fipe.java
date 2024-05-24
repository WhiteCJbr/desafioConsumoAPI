package com.desafio.desafioSpringAPI.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fipe(@JsonAlias("Valor") String valor,
                   @JsonAlias("Marca") String marca,
                   @JsonAlias("Modelo") String modelo,
                   @JsonAlias("AnoModelo") String anomodelo,
                   @JsonAlias("Combustivel") String combustivel,
                   @JsonAlias("CodigoFipe") String codigofipe,
                   @JsonAlias("MesReferencia") String mesreferencia,
                   @JsonAlias("SiglaCombustivel") String siglacombustivel) {
}
