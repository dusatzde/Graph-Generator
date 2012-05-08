/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ZDENEK
 */
public class GeneralActionListener implements ActionListener{
    
    private String aboutMesage = "About About About";
    
    @Override
    public void actionPerformed(ActionEvent e) {
         String cmd = e.getActionCommand();
         if (cmd.equals("exit")) {
            System.exit(0);
        } else if (cmd.equals("about")) {
             JOptionPane.showMessageDialog(null, aboutMesage, "About", JOptionPane.PLAIN_MESSAGE);
        } 
    }
    
}
