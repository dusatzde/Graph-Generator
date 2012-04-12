/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author ZDENEK
 */
public class MainWindow extends JFrame{

    private BufferedImage icon;

    public MainWindow() {
        super();
        init();
    }

    private void init() {
            Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds(screenDimension.width / 2 - 400, screenDimension.height / 2 - 300, 800, 600);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setResizable(false);
            setTitle("Graph Generator 1.0");
            setIcon("icon.png");
            

            GraphicsPanel logoPanel = new GraphicsPanel();
            LogPanel logPanel = new LogPanel();
            MainMenuBar menuBar = new MainMenuBar();
            OutputPanel outputPanel = new OutputPanel();
            ConfigPanel configPanel = new ConfigPanel();
            
            setJMenuBar(menuBar);
            add(logoPanel, BorderLayout.NORTH);
            add(logPanel, BorderLayout.SOUTH);
            add(outputPanel, BorderLayout.EAST);
            //add(configPanel, BorderLayout.WEST);
    }
    
    private void setIcon(String path){
        try {
            icon = ImageIO.read(new File("icon.png"));
            setIconImage(icon);
        } catch (IOException ex) {}
    }
}
