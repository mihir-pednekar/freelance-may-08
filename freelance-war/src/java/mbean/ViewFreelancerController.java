package mbean;
import entities.Freelancer;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.FreelancerModel;
import svc.JobsSvcImpl;

@Named(value = "viewFreeCtrl")
@RequestScoped
@ManagedBean
public class ViewFreelancerController 
{
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private FreelancerModel free;
    
    public ViewFreelancerController() {
    }

    public FreelancerModel getFree() {
        return free;
    }

    public void setFree(FreelancerModel free) {
        this.free = free;
    }

    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long userid = Long.parseLong(req.getParameter("freeid"));
        setFree(new FreelancerModel());
        Freelancer freeEntity = jobsSvcImpl.getFreelancersById(new Freelancer(userid));
        FreelancerModel freeModel = new FreelancerModel( String.valueOf(freeEntity.getUid()) , freeEntity.getSkills(), freeEntity.getMessage(),
                freeEntity.getUsers().getFirstname(), freeEntity.getUsers().getLastname(), freeEntity.getUsers().getUserRole(), freeEntity.getUsers().getUsername());
        setFree(freeModel);
    }
    
}
