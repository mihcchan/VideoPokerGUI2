import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe que cria a janela inicial do jogo.
 */
public class VideoPokerGUI extends JFrame {

    private JTextField nome;

    /**
     * Construtor da classe VideoPokerGUI que que cria a janela inicial do jogo com as instruções,
     * o saldo e um campo para digitar a aposta a ser feita.
     * @constructor 
     * @param saldo saldo atual.
     */
    public VideoPokerGUI(Creditos saldo) {
        super("Video Poker");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setLayout(new GridLayout(5,1));
        jPanel.add(new JLabel("<html>" + "Feche a janela para sair." + "<br>" +
                "Quando o saldo chegar a 0 o jogo terminará." + "</html>", SwingConstants.CENTER));
        jPanel.add(new JLabel("Saldo atual: " + saldo.getSaldo(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Digite o valor da aposta:", SwingConstants.CENTER));

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


        this.setVisible(true);


        synchronized(buttonOK) {
            try {
                buttonOK.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }


    }

    /**
     * Retorna o valor da aposta digitada no campo de texto.
     * @return aposta
     */
    public int getAposta (){
        
        int aposta = Integer.parseInt(this.nome.getText());

        return aposta;
    }
 
}
