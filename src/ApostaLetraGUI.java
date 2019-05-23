import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que cria uma janela dizendo para apostar um número.
 */
public class ApostaLetraGUI extends JFrame {


    /**
     * Construtor da classe ApostaLetraGUI que cria uma janela dizendo para apostar um número e um botão de OK.
     * @constructor 
     */
    public ApostaLetraGUI() {
        super("Erro");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,1));


        JLabel aviso = new JLabel("Por favor digite um número.", SwingConstants.CENTER);
        JLabel aviso1 = new JLabel("Pressione OK para continuar.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

        this.setVisible(true);


        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                ApostaLetraGUI.super.dispose();
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
