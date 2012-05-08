/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import cz.cvut.generator.gui.util.Components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ZDENEK
 */
public class LogPanel extends JTabbedPane{
    
    private  JTextArea ta;
    private JComponent logPanel;
    
    public LogPanel(){
       init();
       
    }
    
    private void init(){
        ta = new JTextArea();
        ta.setName("LogTextArea");
        Components.component.put(ta.getName(), ta);
        ta.setText("Welcome. Choose type and size of your graph...");
        ta.setLineWrap(true);
        ta.setEditable(false);
        Font f = Font.decode("Monospaced");
        if (f != null) {
            ta.setFont(f);
        }
        
        logPanel = new JPanel(false);
        FlowLayout fl = new FlowLayout();
        fl.setVgap(0);
        logPanel.setLayout(fl);
        
        JScrollPane sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(790, 90));
        logPanel.setPreferredSize(new Dimension(780, 90));
        logPanel.setBackground(Color.red);
        logPanel.add(sp);
        this.addTab("Log", null, logPanel, "Log Area");
    }
}
