import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que cria uma janela dizendo que a aposta foi maior que o saldo.
 */
public class ApostaMaiorQueSaldoGUI extends JFrame {

    /**
     * Construtor da classe ApostaMaiorQueSaldoGUI que cria uma janela dizendo que a aposta foi maior que o saldo
     * informando o saldo atual e um botão e OK.
     * @constructor 
     * @param saldo
     */
    public ApostaMaiorQueSaldoGUI(Creditos saldo) {

        super("Erro");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));

        


        JLabel aviso = new JLabel("Aposte um valor menor que seu saldo.", SwingConstants.CENTER);
        JLabel aviso2 = new JLabel("Seu saldo atual é: " + saldo.getSaldo(), SwingConstants.CENTER);
        JLabel aviso3 = new JLabel("Pressione OK para continuar.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
               ApostaMaiorQueSaldoGUI.super.dispose();
                synchronized (botao) {
                    botao.notify();
                }

            }
        });

        botao.setText("OK");

        this.add(aviso);
        this.add(aviso2);
        this.add(aviso3);
        this.add(botao);

        this.setVisible(true);


        synchronized(botao) {
            try {
                botao.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }
    }
}
