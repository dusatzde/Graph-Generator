/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author ZDENEK
 */
public class ConfigPanel extends JPanel{
    
    private JLabel vertex_count_label = new JLabel("Vertex count:");
    private JTextField vertex_count = new JTextField();
    
    private Border border;
    
    public ConfigPanel(){
        border = BorderFactory.createTitledBorder("Graph Configuration");
        setBorder(border);
        
        GridBagLayout formLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 5, 3, 5);
        this.setLayout(formLayout);
        
        gbc.gridx = 0;
        gbc.gridy= 0;
        this.add(vertex_count_label, gbc);
        gbc.gridx = 1;
        gbc.gridy= 0;
        this.add(vertex_count, gbc);
    }
     
}
