import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Checkbox extends JFrame {
    
    private JCheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private CheckboxTratador tratador;



    public Checkbox(Baralho cartas) {
        
        super("Cartas sorteadas");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6, 1));
        this.setVisible(true);
        
        this.add(new JLabel("Escolha quais cartas deseja trocar:", SwingConstants.CENTER));
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
                Checkbox.super.dispose();
                synchronized (botao) {
                    botao.notify();
                }
                
            }
        }); 

        botao.setText("CONFIRMA");

        this.add(botao);

        synchronized(botao) {
            try {
                botao.wait();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        }

        System.out.println(checkBox1.isSelected());
        

        

    }
    
   
    
    public String getStringDasCheckbox(){
        return tratador.getStringParaSortear();
    }

    private class CheckboxTratador implements ItemListener {


        private String stringParaSortear;

        

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
            
            System.out.println(this.stringParaSortear);
        }

        public String getStringParaSortear() {
            return this.stringParaSortear;
        }
        
    }
    
    
}
