package com.desafio.desafioSpringAPI.principal;

import com.desafio.desafioSpringAPI.model.DadosCarros;
import com.desafio.desafioSpringAPI.model.Dados;
import com.desafio.desafioSpringAPI.model.Fipe;
import com.desafio.desafioSpringAPI.service.ConsumoAPI;
import com.desafio.desafioSpringAPI.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitor = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1";
    private String complemento = "";


    public void exibePrincipal() {
        System.out.println("""
                Digite uma das opções desejadas:
                    [1] Carro
                    [2] Moto
                    [3] Caminhão
                """);
        System.out.print("Digite a opção: ");
        var opcao = leitor.nextInt();
        leitor.nextLine();

        switch (opcao) {
            case 1: complemento = "/carros/marcas"; break;
            case 2: complemento =  "/motos/marcas"; break;
            case 3: complemento =  "/caminhoes/marcas"; break;
        }

        var json = consumoAPI.obterDados(ENDERECO + complemento);

        var marcas = converteDados.obterListaDados(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o codigo do modelo desejado: ");
        var codigo = leitor.nextLine();

        var modelo =  marcas.stream()
                .filter(dados -> dados.codigo().equals(codigo))
                .findFirst();


        complemento += "/"+modelo.get().codigo()+"/modelos";
        json = consumoAPI.obterDados(ENDERECO + complemento);
        var carros = converteDados.obterDados(json, DadosCarros.class);
        carros.modelos().forEach(System.out::println);


        System.out.print("Digite parte do nome do carro desejado: ");
        var nomeC = leitor.nextLine();
        carros.modelos().stream()
                .filter(m -> m.modelo().toUpperCase().contains(nomeC.toUpperCase()))
                .forEach(System.out::println);

        System.out.print("Digite o codigo do carro desejado: ");
        var escolhido = leitor.nextLine();

        complemento += "/"+escolhido+"/anos";
        json = consumoAPI.obterDados(ENDERECO + complemento);
        var anos = converteDados.obterListaDados(json, Dados.class);

        System.out.println("Todos os dados FIPE do veículo solicitado: ");
        List<Fipe> fipes = new ArrayList<>();
        anos.forEach(d -> {
            var ano = "/"+d.codigo();
            var json2 = consumoAPI.obterDados(ENDERECO + complemento + ano);
            fipes.add(converteDados.obterDados(json2, Fipe.class));
        });
        fipes.forEach(System.out::println);

    }
}
