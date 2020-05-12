package mbean;
import entities.Freelancer;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import model.FreelancerModel;
import svc.JobsSvcImpl;

@Named(value = "viewFreelancerCont")
@RequestScoped
@ManagedBean
public class ViewFreelancerController 
{
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private List<Freelancer> freelancerList;
    private List<FreelancerModel> freelancerModelList;
    
    public ViewFreelancerController() {
    }

    public JobsSvcImpl getJobsSvcImpl() {
        return jobsSvcImpl;
    }

    public void setJobsSvcImpl(JobsSvcImpl jobsSvcImpl) {
        this.jobsSvcImpl = jobsSvcImpl;
    }

    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }

    public void setFreelancerList(List<Freelancer> freelancerList) {
        this.freelancerList = freelancerList;
    }

    public List<FreelancerModel> getFreelancerModelList() {
        return freelancerModelList;
    }

    public void setFreelancerModelList(List<FreelancerModel> freelancerModelList) {
        this.freelancerModelList = freelancerModelList;
    }
    
    @PostConstruct
    public void getFreelancersByJobId(){
        //HttpSession session = SessionUtils.getSession();
         //  Long userid= (Long) session.getAttribute("user_id");

        //freelancerList = jobsSvcImpl.getFreelancersByJobId());
        
        //this.setJobsList(jobsList);
        //return jobsList;
        
         freelancerList.forEach((um) -> {
         FreelancerModel model=new FreelancerModel(String.valueOf(um.getUid()),um.getSkills(), um.getMessage(), um.getUsers().getFirstname(), um.getUsers().getLastname(), um.getUsers().getUserRole(),um.getUsers().getUsername());
                

                //else
                   // setDisable(false);

            });  
    }
    
}
