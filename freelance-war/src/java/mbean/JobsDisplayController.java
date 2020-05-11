/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import svc.JobsSvcImpl;
import entities.Jobs;
import entities.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import model.JobsModel;
import utils.SessionUtils;


@Named(value = "jobsDisp")
@SessionScoped
@ManagedBean
public class JobsDisplayController implements Serializable {

    /**
     * Creates a new instance of JobsDisplayController
     */
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private List<Jobs> jobsList;
    private List<JobsModel> jobModelList=new ArrayList<>();

   // private boolean disable=true;
    
    
    public JobsDisplayController() {
        jobsSvcImpl = new JobsSvcImpl();
       //getAllOpenJobs();
      //List<Jobs> listOfJobs = getJobsByProv();
      //setJobsList(listOfJobs);
    }

 

    public JobsSvcImpl getJobsSvcImpl() {
        return jobsSvcImpl;
    }

    public void setJobsSvcImpl(JobsSvcImpl jobsSvcImpl) {
        this.jobsSvcImpl = jobsSvcImpl;
    }

    public List<Jobs> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }
    
    public void getAllJobs(){
        jobsList = getJobsSvcImpl().getAllJobs();
    }
    
    public void getAllOpenJobs(){
        jobsList = jobsSvcImpl.getAllOpenJobs();
    }

    public List<JobsModel> getJobModelList() {
        return jobModelList;
    }
        
    @PostConstruct
    public void getJobsByProv(){
        HttpSession session = SessionUtils.getSession();
           Long userid= (Long) session.getAttribute("user_id");

        jobsList = jobsSvcImpl.getJobsByProv(new Provider(userid));
        
        this.setJobsList(jobsList);
        //return jobsList;
        
         jobsList.forEach((um) -> {
         JobsModel model=new JobsModel(String.valueOf(um.getJobid()), um.getTitle(), um.getSkills(), um.getDescription(), String.valueOf(um.getPayment()), um.getJobstatus(), String.valueOf(um.getCreatedby()));
                if(um.getJobstatus().toUpperCase().contains("OPEN"))
                {
                   model.setIsDisable(false);
                }
                else
                   model.setIsDisable(true);

                jobModelList.add(model);

                //else
                   // setDisable(false);

            });  
    }
    
    
    public void onClickJobId(String jobId)
    {
        jobModelList.forEach((um) -> {
        if(um.getJobid().compareTo(jobId)==0)
        {
            um.setIsClicked(true);
        }
        });  
        
    }
    
}
