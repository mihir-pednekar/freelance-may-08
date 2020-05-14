/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomValidator;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import svc.UsersSvcImpl;

/**
 *
 * @author OMEN
 */
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator{
    private final UsersSvcImpl usersSvcImpl = new UsersSvcImpl();
    private List<String> usernames;
    
    @Override
    public void validate(FacesContext context, UIComponent c, Object val){
        usernames = usersSvcImpl.getAllUsernames();
        boolean error = false;
        String username = (String) val;
        String errorDetail = "";
        String errorSummary = "";
        if(username.isEmpty()){
            error = true;
            errorDetail = "Please enter username";
            errorSummary = "username cannot be empty";
        }
        else if(usernames.contains(username.toLowerCase())){
            error = true;
            errorDetail = "Please enter a different username";
            errorSummary = "username already taken";
        }
        if(error){
            FacesMessage message = new FacesMessage();
            message.setDetail(errorDetail);
            message.setSummary(errorDetail + "; " + errorSummary);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
