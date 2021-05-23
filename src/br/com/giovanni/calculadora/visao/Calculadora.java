package br.com.giovanni.calculadora.visao;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {

    public Calculadora() {

        organizarLayout();

//        setUndecorated(true);     para retirar a barra padrao de janelas so sistema operacional

        setSize(232, 322);//tamanhop da tela da aplicação
        setDefaultCloseOperation(EXIT_ON_CLOSE);// fechar a aplicacao assim que clicar no botao x
        setLocationRelativeTo(null); // colocar no centro da tela
        setVisible(true); //visibilidade da tela
    }

    private void organizarLayout() {
        setLayout(new BorderLayout());

        Display display = new Display();
        display.setPreferredSize(new Dimension(233,60));//define uma nova dimensao padrao para o display pois senao ele ficaria muito pequeno
        add(display, BorderLayout.NORTH);//aplica a cor definida na classe na tela, e define a
        // posicao que ela ficara, pois se nao fizermos assim uma ficara sobreposta sobre a outra

        Teclado teclado = new Teclado();
        add(teclado, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
