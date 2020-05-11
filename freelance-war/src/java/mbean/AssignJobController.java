package mbean;
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
import model.AssignJobModel;
import model.JobsModel;
import svc.JobsSvcImpl;


@Named(value = "assignJobController")
@RequestScoped
public class AssignJobController implements Serializable {
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    List<AssignJobModel> assignJobModelList=new ArrayList<>();
    
    @ManagedProperty(value="#{jobsDisp}")
    private JobsDisplayController jobDispCont;
    
    
    public AssignJobController() {
    }

    public JobsSvcImpl getJobsSvcImpl() {
        return jobsSvcImpl;
    }

    public void setJobsSvcImpl(JobsSvcImpl jobsSvcImpl) {
        this.jobsSvcImpl = jobsSvcImpl;
    }

    public List<AssignJobModel> getAssignJobModelList() {
        return assignJobModelList;
    }

    public void setAssignJobModelList(List<AssignJobModel> assignJobModelList) {
        this.assignJobModelList = assignJobModelList;
    }
    
    @PostConstruct
    public void getFreelancersByJobId(){
          List<JobsModel> modellist=jobDispCont.getJobModelList();
       
           modellist.forEach((um) -> {
               if(um.isIsClicked())
               {
                List<Jobapps> jobAppsList= jobsSvcImpl.getFreelancersByJobId(new Jobs(Long.parseLong(um.getJobid())));
                 jobAppsList.forEach((apps) -> {
                     getAssignJobModelList().add(new AssignJobModel(um.getJobid(), um.getTitle(), um.getDescription(), um.getJobstatus(), String.valueOf(apps.getFid())));
                  
                 });
               }
       
           });  
    }
    
}
