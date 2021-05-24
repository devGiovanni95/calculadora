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

    private TipoComando ultimaOperacao = null;
    private boolean substituir = false;
    private String textoAtual = "";
    private String textoBuffer = "";


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

        if (tipoComando == null){
            return;
        }else if (tipoComando == TipoComando.ZERAR){
            textoAtual = "";
            textoBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        }else if (tipoComando == TipoComando.NUMERO ||tipoComando == TipoComando.VIRGULA){
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        }else {
            substituir = true;
            textoAtual = obterResultadoOperacao();
            textoBuffer = textoAtual;
            ultimaOperacao = tipoComando;

        }
//        System.out.println(tipoComando);
//        if ("AC".equals(texto)) {
//            textoAtual = "";
//        } else {
//            textoAtual += texto;
//        }

        observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if (ultimaOperacao == null){
            return  textoAtual;
        }

        double numeroBuffer = Double.parseDouble(textoBuffer.replace(",","."));
        double numeroAtual = Double.parseDouble(textoAtual.replace(",","."));

        double resultado = 0;

        if (ultimaOperacao == TipoComando.SOMA){
            resultado = numeroBuffer + numeroAtual;
        }else if (ultimaOperacao == TipoComando.SUBTRACAO){
            resultado = numeroBuffer - numeroAtual;
        }else if (ultimaOperacao == TipoComando.MUTIPLICACAO){
            resultado = numeroBuffer * numeroAtual;
        }else if (ultimaOperacao == TipoComando.DIVISAO){
            resultado = numeroBuffer / numeroAtual;
        }

        String resultadoString = Double.toString(resultado).replace(".", ",");
        boolean inteiro = resultadoString.endsWith(",0");
        return inteiro ? resultadoString.replace(",0",""): resultadoString;


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
                return TipoComando.PORCENTAGEM;
            }else if (",".equals(texto) && !textoAtual.contains(",")){
                return TipoComando.VIRGULA;
            }
        }

        return null;

    }
}
