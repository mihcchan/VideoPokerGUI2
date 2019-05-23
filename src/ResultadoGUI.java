import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ResultadoGUI extends JFrame{

      
    public ResultadoGUI(Baralho cartas, Creditos pontos, int [] numeroCartas, int [] naipesCartas) {
        super("Resultado");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(5, 1));
        this.setVisible(true);

        this.add(new JLabel(""));
        
        this.add(new JLabel("<html>" + "<pre>" + cartas.toString() + "</pre>" + "</html>", SwingConstants.CENTER));

        JLabel resultado = new JLabel("O resultado foi: " + pontos.pontuacao(numeroCartas, naipesCartas), SwingConstants.CENTER);
        
        JLabel aviso = new JLabel("Pressione OK para continuar jogando.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                ResultadoGUI.super.dispose();
                synchronized (botao) {
                    botao.notify();
                }

            }
        });

        botao.setText("OK");
        
        this.add(resultado);
        this.add(aviso);
        this.add(botao);

        synchronized(botao) {
            try {
                botao.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }
    }
}
