import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ApostaInvalidaGUI extends JFrame {
    
    public ApostaInvalidaGUI() {
        super("Erro");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));
        this.setVisible(true);



        JLabel aviso = new JLabel("Aposte um número que não seja 0.", SwingConstants.CENTER);
        JLabel aviso1 = new JLabel("Pressione OK para continuar.", SwingConstants.CENTER);

        JButton botao = new JButton("OK");

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

