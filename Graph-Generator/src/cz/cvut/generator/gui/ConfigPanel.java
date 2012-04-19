/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author ZDENEK
 */
public class ConfigPanel extends JPanel{
    
    private JLabel vertex_count_label = new JLabel("Vertex count:");
    private JLabel graph_type_label = new JLabel("Graph type:");
    private JLabel n_vertex_label = new JLabel("N:");
    private JLabel m_vertex_label = new JLabel("M:");
    
    private JLabel min_weight_label = new JLabel("Min:");
    private JLabel max_weight_label = new JLabel("Max:");
    
    private JTextField vertex_count = new JTextField();
    private JTextField n_vertex_count = new JTextField();
    private JTextField m_vertex_count = new JTextField();
    private JTextField min_weight = new JTextField();
    private JTextField max_weight = new JTextField();
    
    private JCheckBox directed = new JCheckBox("Directed");
    private JCheckBox weighted = new JCheckBox("Weighted");
    
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton simpleGraphType = new JRadioButton("Simple");
    private JRadioButton discreteGraphType = new JRadioButton("Discrete");
    private JRadioButton treeGraphType = new JRadioButton("Tree");
    private JRadioButton cyclicGraphType = new JRadioButton("Cyclic");
    private JRadioButton acyclicGraphType = new JRadioButton("Acyclic");
    private JRadioButton bipartiteGraphType = new JRadioButton("Bipartite");
    
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
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(40, 5, 3, 5);
        this.add(graph_type_label, gbc);
        
        
        initGraphTypes();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(3, 5, 3, 5);
        this.add(simpleGraphType, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(discreteGraphType, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(treeGraphType, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(3, 5, 3, 70);
        this.add(cyclicGraphType, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(3, 5, 3, 5);
        this.add(acyclicGraphType, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(bipartiteGraphType, gbc);
        
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        n_vertex_label.setEnabled(false);
        this.add(n_vertex_label, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 6;
        n_vertex_count.setEditable(false);
        this.add(n_vertex_count, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        m_vertex_label.setEnabled(false);
        this.add(m_vertex_label, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 7;
        m_vertex_count.setEditable(false);
        this.add(m_vertex_count, gbc);
        
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(40, 5, 3, 5);
        this.add(directed, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        this.add(weighted, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets(3, 5, 3, 5);
        min_weight_label.setEnabled(false);
        this.add(min_weight_label, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 9;
        min_weight.setEditable(false);
        this.add(min_weight, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 10;
        max_weight_label.setEnabled(false);
        this.add(max_weight_label, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 10;
        max_weight.setEditable(false);
        this.add(max_weight, gbc);
        
    }
    
    private void initGraphTypes(){
        buttonGroup = new ButtonGroup();    
        simpleGraphType = new JRadioButton("Simple");
        simpleGraphType.setSelected(true);
        
        buttonGroup.add(simpleGraphType);
        discreteGraphType = new JRadioButton("Discrete");
        buttonGroup.add(discreteGraphType);        
        treeGraphType = new JRadioButton("Tree");
        buttonGroup.add(treeGraphType);       
        cyclicGraphType = new JRadioButton("Cyclic");
        buttonGroup.add(cyclicGraphType);    
        acyclicGraphType = new JRadioButton("Acyclic");
        buttonGroup.add(acyclicGraphType);  
        bipartiteGraphType = new JRadioButton("Bipartite");
        buttonGroup.add(bipartiteGraphType);
        
        
    }
     
}
