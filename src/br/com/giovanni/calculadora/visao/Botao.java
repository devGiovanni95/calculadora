package br.com.giovanni.calculadora.visao;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {

    public Botao(String texto, Color cor) {
        setText(texto);//texto
        setFont(new Font("courrier", Font.PLAIN, 25));
        setOpaque(true);// pra poder pintar mesmo
        setBackground(cor);// cor escolhida
        setForeground(Color.WHITE);// cor da fonte escolhida
        setBorder(BorderFactory.createLineBorder(Color.BLACK));//cor das bordas


    }
}
