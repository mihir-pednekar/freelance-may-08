
package mbean;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import svc.UsersSvcImpl;


@Named(value = "removeUserCont")
@RequestScoped
@ManagedBean
public class RemoveUserController {

     @EJB
    private UsersSvcImpl allUsersSvcImpl;
     
    public RemoveUserController() {
    }
    
}
