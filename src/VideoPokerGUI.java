import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VideoPokerGUI extends JFrame {

    private JTextField nome;

    public VideoPokerGUI(Creditos saldo) {
        super("Video Poker");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setLayout(new GridLayout(5,1));
        jPanel.add(new JLabel("Feche a janela para sair.\n"));
        jPanel.add(new JLabel("Saldo atual: " + saldo.getSaldo()));
        jPanel.add(new JLabel("Digite o valor da aposta:"));

        this.nome = new JTextField();
        JButton buttonOK = new JButton("CONFIRMA");
        
        buttonOK.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (buttonOK) {
                    buttonOK.notify();
                }
            }
        });
            
            buttonOK.setText("CONFIRMA");
            
        jPanel.add(nome);
        jPanel.add(buttonOK);

        synchronized(buttonOK) {
            try {
                buttonOK.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }

    }
    
    public int getAposta (){
        
        int aposta = Integer.parseInt(this.nome.getText());

        return aposta;
    }
 
}
