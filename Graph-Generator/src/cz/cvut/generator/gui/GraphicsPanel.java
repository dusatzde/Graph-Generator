/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ZDENEK
 */
public class GraphicsPanel extends JPanel{
    
    private BufferedImage graphics;
    
    public GraphicsPanel(){
        try {
            graphics = ImageIO.read(new File("logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(graphics));
            this.add(logoLabel);
        } catch (IOException ex) {
           
        }
    }
}
