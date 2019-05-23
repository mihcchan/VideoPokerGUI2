import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que cria uma janela anunciando o fim do jogo.
 */
public class FimDeJogoGUI extends JFrame {

    /**
     * Construtor da classe FimDeJogoGUI que cria uma janela anunciando o fim do jogo
     * assim que o saldo chega a 0. O botão de OK encerra o jogo e fecha.
     * @constructor 
     */
    public FimDeJogoGUI() {
        super("Fim de jogo");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));


        JLabel aviso = new JLabel("Seu saldo chegou a 0, o jogo acabou.", SwingConstants.CENTER);
        JLabel aviso1 = new JLabel("Pressione OK para fechar.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

        this.setVisible(true);


        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                FimDeJogoGUI.super.dispose();
                synchronized (botao) {
                    botao.notify();
                }

            }
        });

        botao.setText("OK");

        this.add(aviso);
        this.add(aviso1);
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
