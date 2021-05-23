package br.com.giovanni.calculadora.visao;

import br.com.giovanni.calculadora.modelo.Memoria;
import br.com.giovanni.calculadora.modelo.MemoriaOservador;

import javax.swing.*;
import java.awt.*;

//JPainel e uma classe que agrupa outros componentes
public class Display extends JPanel implements MemoriaOservador {

    private final JLabel label;
    public Display() {
//       setBackground(Color.GREEN);
        Memoria.getInstancia().adicionarObservador(this);

        setBackground(new Color(46,49,50));//colocar cor no fundo do quadro
        label = new JLabel(Memoria.getInstancia().getTextoAtual());//criar texto para adicionar na tela
        label.setForeground(Color.WHITE);//Cor do texto
        label.setFont(new Font("courrier", Font.PLAIN, 30));//nome da fonte/stilo(negrito)/tamanho

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));//alinhamento do texto pode se usar um valor
        // tipo = 2, ou as contantes da propria classe FlowLayout

        add(label);//adiciona na tela
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }
}
