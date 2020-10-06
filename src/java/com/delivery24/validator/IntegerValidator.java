/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.validator;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Wilson Carvajal
 */
@FacesValidator(value="integerValidator")
public class IntegerValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    
        String v = String.valueOf(value);
        if (v != null && !v.isEmpty()) {
            FacesMessage msg;
            if(v.length()>9)
            {
                String msgString = String.format(ResourceBundle.getBundle("/Bundle").getString("TextLengthMax"), 9);
                msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msgString,
                msgString);
                throw new ValidatorException(msg);
            }
            else {
                try {
                    int number = Integer.parseInt(v);
                    if (number < 0) {
                        String msgString = ResourceBundle.getBundle("/Bundle").getString("FieldIntegerPositive");
                        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                msgString,
                                msgString);
                        throw new ValidatorException(msg);
                    }
                } catch (NumberFormatException n) {
                    String msgString = ResourceBundle.getBundle("/Bundle").getString("FieldIntegerPositive");
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            msgString,
                            msgString);
                    throw new ValidatorException(msg);
                }
            }
        }
    }
    
}
