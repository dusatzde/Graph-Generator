/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import cz.cvut.generator.gui.listeners.GenerateActionListener;
import cz.cvut.generator.gui.util.Components;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author ZDENEK
 */
public class OutputPanel extends JPanel implements ActionListener{
    private String[] formats = {"Trivial Graph Format", "DOT", "Incidence Matrix", "Adjacency matrix"};
    private JLabel file_format_label = new JLabel("Select output format:");
    private JComboBox  file_format = new JComboBox(formats);
    private JLabel file_name_label = new JLabel("File name:");
    private JTextField file_name = new JTextField();
    private JLabel file_path_label = new JLabel("Path:");
    private JTextField dir = new JTextField();
    private JButton browseButton = new JButton("Browse");
    private JButton stopButton = new JButton("Stop");
    private JButton generateButton = new JButton("Generate");  
    private Border border;
    private JLabel file_path_err_msg = new JLabel("<html><span style='color: red;'>File name and path required!</span></html>");
   
    
    public OutputPanel(){
        border = BorderFactory.createTitledBorder("Output Configuration");
        setBorder(border);
        GridBagLayout formLayout = new GridBagLayout();
        this.setLayout(formLayout);
        componentsInit();
        componentsLayoutInit();
         Components.component.put("fileFormat", file_format);
         Components.component.put("fileName", file_name);
         Components.component.put("dir", dir);
         Components.component.put("generateButton", generateButton);
         Components.component.put("stopButton", stopButton);
         Components.component.put("filePathErrMsg", file_path_err_msg);
         Components.component.put("fileFormat", file_format);
    }
    
    private void componentsLayoutInit(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 5, 3, 5);
        
        gbc.gridx = 0;
        gbc.gridy= 0;
        this.add(file_format_label, gbc);
        gbc.gridx = 1;
        gbc.gridy= 0;
        this.add(file_format, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(file_name_label, gbc);
        gbc.gridx = 1;
        gbc.gridy= 1;
        this.add(file_name, gbc);
        gbc.gridx = 0;
        gbc.gridy= 2;
        this.add(file_path_label, gbc);
        gbc.gridx = 1;
        gbc.gridy= 2;
        this.add(dir, gbc);
        
        gbc.gridx = 1;
        gbc.gridy= 3;
        file_path_err_msg.setVisible(false);
        this.add(file_path_err_msg, gbc);
        
        gbc.gridx = 2;
        gbc.gridy= 2;
        this.add(browseButton, gbc);
        gbc.gridx = 2;
        gbc.gridy= 5;
        gbc.insets = new Insets(180, 10, 3, 5);
        this.add(generateButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy= 5;
        gbc.insets = new Insets(180, 10, 3, 5);
        stopButton.setVisible(false);
        this.add(stopButton, gbc);
    }
    
    private void componentsInit(){
        GenerateActionListener generateListener = new GenerateActionListener();
        browseButton.setActionCommand("browse");
        browseButton.addActionListener(this);
        
        generateButton.setActionCommand("generate");
        generateButton.addActionListener(generateListener);
        
        
        file_name.setEditable(false);
      
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("browse")) {
            JFileChooser c = new JFileChooser();
            ((JLabel)Components.component.get("filePathErrMsg")).setVisible(false);
            int rVal = c.showSaveDialog(this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                file_name.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
   
        }else if (cmd.equals("exit")) {
            System.exit(0);
        } 
    }
}
