package mbean;
import entities.Freelancer;
import entities.Jobapps;
import entities.Jobs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.AssignJobModel;
import model.JobsModel;
import svc.JobsSvcImpl;


@Named(value = "assignJobController")
@RequestScoped
public class AssignJobController implements Serializable {
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private List<AssignJobModel> assignJobModelList=new ArrayList<>();
    private List<Jobs> jobList; 
    
    public AssignJobController() {
    }

    public List<AssignJobModel> getAssignJobModelList() {
        return assignJobModelList;
    }

    public void setAssignJobModelList(List<AssignJobModel> assignJobModelList) {
        this.assignJobModelList = assignJobModelList;
    }

    public List<Jobs> getJobList() {
        return jobList;
    }

    public void setJobList(List<Jobs> jobList) {
        this.jobList = jobList;
    }
    
    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long jobid = Long.parseLong(req.getParameter("jobid"));
        setAssignJobModelList(new ArrayList<>());
        List<Jobapps> jobAppsList = jobsSvcImpl.getFreelancersByJobId(new Jobs(jobid));
            for( Jobapps apps : jobAppsList){
                if( apps.getJobid().getJobid().compareTo(jobid) == 0){
                    getAssignJobModelList().add(new AssignJobModel( String.valueOf(apps.getJobid().getJobid()), apps.getJobid().getTitle(), 
                      apps.getJobid().getDescription(), apps.getJobid().getJobstatus(), String.valueOf(apps.getFid().getUid()), apps.getFid().getUsers().getFirstname()));
                }
            }
    }
}
