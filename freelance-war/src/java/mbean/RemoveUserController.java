
package mbean;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.PostRemove;
import svc.JobsSvcImpl;
import svc.UsersSvcImpl;


@Named(value = "removeUserCont")
@RequestScoped
@ManagedBean
public class RemoveUserController {

    @EJB
    private UsersSvcImpl allUsersSvcImpl;
    
    @EJB
    private JobsSvcImpl jobsSvcImpl;
     
    public RemoveUserController() 
    {
    }
   
   
    public void deleteProviderByID(Long pid)
    {
        allUsersSvcImpl.deleteProviderByID(pid);   
    }
    
    
    public void deleteFreelancerByID(Long fid)
    {
        allUsersSvcImpl.deleteFreelancerByID(fid);   
    }
    
    public void deleteJobsByjobID(Long jid)
    {
        jobsSvcImpl.deleteJobsByJid(jid);   
    }
       
    
}
