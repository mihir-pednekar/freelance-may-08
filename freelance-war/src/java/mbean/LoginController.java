
package mbean;

import com.constants.ValidationConstants;
import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import svc.LoginSvcImpl;
import utils.SessionUtils;


@Named(value = "loginCtrl")
@SessionScoped
public class LoginController implements Serializable {

//    @Resource(mappedName = "jms/FreelanceDestQueue")
//    private Queue freelanceDestQueue;
//
//    @Inject
//    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
//    private JMSContext context;

    @EJB
    private LoginSvcImpl loginSvcImpl;
    private String user;
    private String passwd;
    private String role;
    private Users currentUser;

    
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
//        loginSvcImpl = new LoginSvcImpl();
        List<Users> userModelList = loginSvcImpl.validateUserFromDB(user, passwd);
        if(userModelList != null){
            HttpSession session = SessionUtils.getSession();
            
            //publish to JMS Queue..
            sendJMSMessageToFreelanceDestQueue("Username "+user+" has login successfully.");
            userModelList.forEach((um) -> {
            session.setAttribute("username", user);
            session.setAttribute("user_id", um.getId());
                this.setRole(um.getUserRole());
                this.currentUser = um;
            });     
            if(role.equals("freelancer"))
                return "freelancerHome";
            else if(role.equals("provider"))
                return "providerHome";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							ValidationConstants.INVALID_USER,
							ValidationConstants.REQ_VALID_USER_PASS));
            return "index";
        }
        return null;
    }

    private void sendJMSMessageToFreelanceDestQueue(String messageData) {
        //context.createProducer().send(freelanceDestQueue, messageData);
    }
    
    public String getFullName(){
        if(currentUser != null){
            return currentUser.getFirstname() + " " + currentUser.getLastname();
        }
        else{
            return null;
        }
    }
    public Long getUserId(){
        if(currentUser != null){
            return currentUser.getId();
        }
        else{
            return null;
        } 
    }
    public String getUsername(){
        if(currentUser != null){
            return currentUser.getUsername();
        }
        else{
            return null;
        } 
    }
    public String getUserRole(){
        if(currentUser != null){
            return currentUser.getUserRole();
        }
        else{
            return null;
        } 
    }
    public String getUserSkills(){
        if(currentUser != null){
            return currentUser.getFreelancer().getSkills();
        }
        else{
            return null;
        } 
    }
    public String getUserMessage(){
        if(currentUser != null){
            return currentUser.getFreelancer().getMessage();
        }
        else{
            return null;
        } 
    }
}
