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
public class IntegerRequiredValidator {
    
    public static int isValid(JTextField textField) throws IntegerValidatorException {
        String text = textField.getText();
        try{
            int value = Integer.parseInt(text);
            if(value <= 0)
                throw new IntegerValidatorException("Invalid format!");
            return value;
        }catch(NumberFormatException e){
            throw new IntegerValidatorException("Invalid format!");
        }
        
    }
}
