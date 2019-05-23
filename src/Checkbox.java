import javax.swing.*;
import java.awt.*;

public class Checkbox extends JFrame {
    

    public Checkbox(Baralho cartas) {
        super("Quais cartas deseja escolher?");
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new GridLayout(3, 1));

        this.add(new JLabel("<html>" + "<pre>" + cartas.toString() + "</pre>" + "</html>"));

        JPanel checkboxes = new JPanel();
        checkboxes.setLayout(new FlowLayout());
        JCheckBox checkBox1 = new JCheckBox("Carta 1");
        JCheckBox checkBox2 = new JCheckBox("Carta 2");
        JCheckBox checkBox3 = new JCheckBox("Carta 3");
        JCheckBox checkBox4 = new JCheckBox("Carta 4");
        JCheckBox checkBox5 = new JCheckBox("Carta 5");
        
        checkboxes.add(checkBox1);
        checkboxes.add(checkBox2);
        checkboxes.add(checkBox3);
        checkboxes.add(checkBox4);
        checkboxes.add(checkBox5);
        
        this.add(checkboxes);
        
        this.add(new JButton());

    }
}
