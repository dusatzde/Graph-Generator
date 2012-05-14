/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui.listeners;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ZDENEK
 */
public class GeneralActionListener implements ActionListener{
    
    private String aboutMesage = "The Graph Generator project is a new open source project which "
            + "manages generating of graphs of various types. These graphs can be used"
            + " as an input data for testing your graph algorithms.\n\n"
            + "Graph generator generates graphs according to its user wishes, "
            + "e.g. complete graphs, discrete graphs, trees, simple acyclic graphs, etc.\n\n"
            + "The output file format is optional. Graph generator offers well-known formats for "
            + "graph representation - Trivial Graph Format, Graph Modelling Language and DOT for "
            + "graph visualization in Graphviz software."
            + "\n\n"
            + "CVUT FEL 2012\n";
    
    @Override
    public void actionPerformed(ActionEvent e) {
         String cmd = e.getActionCommand();
         if (cmd.equals("exit")) {
            System.exit(0);
        } else if (cmd.equals("about")) {
            JPanel msgPanel = new JPanel();
            
            JTextArea textArea = new JTextArea(aboutMesage);  
            textArea.setSize(400, 300);
            textArea.setLineWrap(true);  
            textArea.setWrapStyleWord(true);  
            textArea.setMargin(new Insets(5,5,5,5));
            textArea.setBackground(msgPanel.getBackground());
            JEditorPane link = new JEditorPane("text/html", "<html>Project wiki pages are  <a href=\"https://github.com/dusatzde/Graph-Generator/wiki\">https://github.com/dusatzde/Graph-Generator/wiki</a></html> ");
            link.setBackground(msgPanel.getBackground());
            msgPanel.setLayout(new GridLayout(2, 1));
            msgPanel.add(textArea);
            msgPanel.add(link);
            JOptionPane.showMessageDialog(null, msgPanel, "About", JOptionPane.PLAIN_MESSAGE);
        } 
    }
    
}
