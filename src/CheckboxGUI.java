import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe que cria 5 checkbox para selecionar quais cartas deseja trocar.
 */
public class CheckboxGUI extends JFrame {
    
    private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private CheckboxTratador tratador;


    /**
     * Construtor da classe CheckboxGUI que cria 5 checkbox para selecionar quais cartas deseja trocar
     * juntamente com um botão de confirmar.
     * @constructor 
     * @param cartas cartas da rodada atual.
     */
    public CheckboxGUI(Baralho cartas) {
        
        super("Cartas sorteadas");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6, 1));
        
        this.add(new JLabel("<html>" + "Escolha quais cartas deseja trocar." + "<br>" +
                "Para não trocar basta não selecionar nenhuma carta." + "</html>", SwingConstants.CENTER));
        this.add(new JLabel(""));
        this.add(new JLabel("<html>" + "<pre>" + cartas.toString() + "</pre>" + "</html>", SwingConstants.CENTER));
        
        JPanel checkboxes = new JPanel();
        checkboxes.setLayout(new FlowLayout());
        this.checkBox1 = new JCheckBox("Carta 1");
        this.checkBox2 = new JCheckBox("Carta 2");
        this.checkBox3 = new JCheckBox("Carta 3");
        this.checkBox4 = new JCheckBox("Carta 4");
        this.checkBox5 = new JCheckBox("Carta 5");
        
        this.tratador = new CheckboxTratador();

        checkBox1.addItemListener(tratador);
        
        checkBox2.addItemListener(tratador);
        checkBox3.addItemListener(tratador);
        checkBox4.addItemListener(tratador);
        checkBox5.addItemListener(tratador);
        
        
        checkboxes.add(checkBox1);
        checkboxes.add(checkBox2);
        checkboxes.add(checkBox3);
        checkboxes.add(checkBox4);
        checkboxes.add(checkBox5);
        
        this.add(checkboxes);
        
        this.add(new JLabel(""));
        
        JButton botao = new JButton("CONFIRMA");

        botao.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                CheckboxGUI.super.dispose();
                synchronized (botao) {
                    botao.notify();
                }
                
            }
        }); 

        botao.setText("CONFIRMA");

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


    /**
     * Retorna a string formada pelas checkbox
     * @return string
     */
    public String getStringDasCheckbox(){
        return tratador.getStringParaSortear();
    }

    /**
     * Classe que implementa o ItemListener das checkbox
     */
    private class CheckboxTratador implements ItemListener {


        private String stringParaSortear;


        /**
         * Override de itemStateChanged para que cada checkbox crie uma string
         * @param itemEvent
         */
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            this.stringParaSortear = "";
            
            if(checkBox1.isSelected()){
                this.stringParaSortear += "1 ";
            }
            if(checkBox2.isSelected()){
                this.stringParaSortear += "2 ";
            }
            if(checkBox3.isSelected()){
                this.stringParaSortear += "3 ";
            }
            if(checkBox4.isSelected()){
                this.stringParaSortear += "4 ";
            }
            if(checkBox5.isSelected()){
                this.stringParaSortear += "5 ";
            }
      
        }

        /**
         * Retorna a string formada ao selecionar cartas que deseja trocar.
         * @return string
         */
        public String getStringParaSortear() {
            if(checkBox1.isSelected() || checkBox2.isSelected() || checkBox3.isSelected() || checkBox4.isSelected() || checkBox5.isSelected()){
                return this.stringParaSortear;

            } else {
                String s = " ";
                return s;
            }
        }
        
    }
    
    
}
