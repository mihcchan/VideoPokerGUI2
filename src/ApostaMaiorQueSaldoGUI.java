import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ApostaMaiorQueSaldoGUI extends JFrame {

    public ApostaMaiorQueSaldoGUI(Creditos saldo) {

        super("Erro");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));
        this.setVisible(true);

        


        JLabel aviso = new JLabel("Aposte um número menor que seu saldo.", SwingConstants.CENTER);
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

        synchronized(botao) {
            try {
                botao.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }
    }
}
