package br.com.giovanni.calculadora.modelo;

@FunctionalInterface
public interface MemoriaOservador {

    public void valorAlterado(String novoValor);
}
