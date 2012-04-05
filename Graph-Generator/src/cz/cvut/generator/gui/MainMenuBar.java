/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author ZDENEK
 */
public class MainMenuBar extends JMenuBar implements ActionListener{
    
    private String aboutMesage = "About About About";
    
    private JMenu file;
    private JMenu help;
    private JMenuItem aboutMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem generateMenuItem;
    
    
    public MainMenuBar(){
        super();
        init();
    }
    
    private void init(){
        file = new JMenu("File");
        help = new JMenu("Help");
        
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setActionCommand("about");
        aboutMenuItem.addActionListener(this);
        help.add(aboutMenuItem);
        
        generateMenuItem = new JMenuItem("Generate");
        generateMenuItem.setActionCommand("generate");
        generateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
        generateMenuItem.addActionListener(this);
        file.add(generateMenuItem);
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("exit");
        exitMenuItem.addActionListener(this);
        file.add(exitMenuItem);
        
        this.add(file);
        this.add(help);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("generate")) {
            //to do
        } else if (cmd.equals("exit")) {
            System.exit(0);
        } else if (cmd.equals("about")) {
             JOptionPane.showMessageDialog(null, aboutMesage, "About", JOptionPane.PLAIN_MESSAGE);
        } 
    }
    
}
