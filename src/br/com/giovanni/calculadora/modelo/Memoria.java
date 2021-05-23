package br.com.giovanni.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private enum TipoComando {
        ZERAR, NUMERO, DIVISAO, MUTIPLICACAO, SUBTRACAO, SOMA, IGUAL, VIRGULA, PORCENTAGEM
    };

    private static final Memoria instancia = new Memoria();

    private final List<MemoriaOservador> observadores =
            new ArrayList<>();

    private String textoAtual = "";

    private Memoria() {

    }

    public static Memoria getInstancia() {
        return instancia;
    }

    public void adicionarObservador(MemoriaOservador observador) {
        observadores.add(observador);
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void processarComando(String texto) {

        TipoComando tipoComando = detectarTipoComando(texto);

        System.out.println(tipoComando);
        if ("AC".equals(texto)) {
            textoAtual = "";
        } else {
            textoAtual += texto;
        }

        observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private TipoComando detectarTipoComando(String texto) {

        if (textoAtual.isEmpty() && texto == "0") {
            return null;
        }

        try {
            Integer.parseInt(texto);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            //Quando não for numero....
            if ("AC".equals(texto)) {
                return TipoComando.ZERAR;
            } else if ("/".equals(texto)){
                return TipoComando.DIVISAO;
            }else if ("*".equals(texto)){
                return TipoComando.MUTIPLICACAO;
            }else if ("+".equals(texto)){
                return TipoComando.SOMA;
            }else if ("-".equals(texto)){
                return TipoComando.SUBTRACAO;
            }else if ("=".equals(texto)){
                return TipoComando.IGUAL;
            }else if ("%".equals(texto)){
                return TipoComando.PORCENTAGEM ;
            }else if (",".equals(texto)){
                return TipoComando.VIRGULA ;
            }
        }

        return null;

    }
}
