/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui.util;

import javax.swing.JTextField;

/**
 *
 * @author ZDENEK
 */
public class DoubleRequiredValidator {
    
     public static double isValid(JTextField textField) throws DoubleValidatorException {
        String text = textField.getText();
        try{
            double value = Double.parseDouble(text);
            if(value <= 0)
                throw new DoubleValidatorException("Invalid format!");
            return value;
        }catch(NumberFormatException e){
            throw new DoubleValidatorException("Invalid format!");
        }
    }
}
