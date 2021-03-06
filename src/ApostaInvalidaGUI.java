import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que cria uma janela dizendo para não apostar 0.
 */
public class ApostaInvalidaGUI extends JFrame {
    
    /**
     * Contrutor da classe ApostaInvalidaGUI que cria uma janela dizendo para não apostar 0 e um botão de OK.
     * @constructor 
     */
    public ApostaInvalidaGUI() {
        super("Erro");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));


        JLabel aviso = new JLabel("Aposte um número que não seja 0.", SwingConstants.CENTER);
        JLabel aviso1 = new JLabel("Pressione OK para continuar.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

        this.setVisible(true);


        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                ApostaInvalidaGUI.super.dispose();
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

