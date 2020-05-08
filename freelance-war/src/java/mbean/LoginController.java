/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import com.constants.ValidationConstants;
import entities.Provider;
import entities.Users;
import hash.RandomString;
import hash.SaltMaker;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.http.HttpSession;
import svc.LoginSvcImpl;
import utils.SessionUtils;

/**
 *
 * @author mihir
 */
@Named(value = "loginCtrl")
@RequestScoped
public class LoginController {

    @Resource(mappedName = "jms/FreelanceDestQueue")
    private Queue freelanceDestQueue;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    @EJB
    private LoginSvcImpl loginSvcImpl;
    private String user;
    private String passwd;
    private String role;

    
    public LoginController() {
    }
    
    public LoginController(String user, String passwd, String role) {
        this.user = user;
        this.passwd = passwd;
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String validateUser() throws NoSuchAlgorithmException{
        List<Users> userModelList = loginSvcImpl.validateUserFromDB(user, passwd);
        if(userModelList != null){
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            //publish to JMS Queue..
            sendJMSMessageToFreelanceDestQueue("Username "+user+" has login successfully.");
            userModelList.forEach((um) -> {
                this.setRole(um.getUserRole());
            });            
            return "success";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							ValidationConstants.INVALID_USER,
							ValidationConstants.REQ_VALID_USER_PASS));
            return "index";
        }
    }

    private void sendJMSMessageToFreelanceDestQueue(String messageData) {
        context.createProducer().send(freelanceDestQueue, messageData);
    }

}
