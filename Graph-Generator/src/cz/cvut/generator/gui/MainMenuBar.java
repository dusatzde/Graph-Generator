/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import cz.cvut.generator.gui.listeners.GeneralActionListener;
import cz.cvut.generator.gui.listeners.GenerateActionListener;
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
public class MainMenuBar extends JMenuBar {
    
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
        GeneralActionListener generalListener = new GeneralActionListener();
        GenerateActionListener generateListener = new GenerateActionListener();
        file = new JMenu("File");
        help = new JMenu("Help");
        
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setActionCommand("about");
        aboutMenuItem.addActionListener(generalListener);
        help.add(aboutMenuItem);
        
        generateMenuItem = new JMenuItem("Generate");
        generateMenuItem.setActionCommand("generate");
        generateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
        generateMenuItem.addActionListener(generateListener);
        file.add(generateMenuItem);
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("exit");
        exitMenuItem.addActionListener(generalListener);
        file.add(exitMenuItem);
        
        this.add(file);
        this.add(help);
    }    
}
