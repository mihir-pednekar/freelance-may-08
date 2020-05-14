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
import entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import model.JobsModel;
import svc.UsersSvcImpl;
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
    @EJB
    private UsersSvcImpl usersSvcImpl;
    private List<Jobs> jobsList=new ArrayList<>();
    private List<Jobs> fJobsList;
    private List<JobsModel> jobModelList=new ArrayList<>();
    int filterCase;
    private String searchStr;

   // private boolean disable=true;
    
    
    public JobsDisplayController() {
        filterCase = 0;
        searchStr = "";
        jobsSvcImpl = new JobsSvcImpl();
        //getJobsByProv();
      //List<Jobs> listOfJobs = getJobsByProv();
      //setJobsList(listOfJobs);
    }

    public List<Jobs> getfJobsList() {
        return fJobsList;
    }

    public void setfJobsList(List<Jobs> fJobsList) {
        this.fJobsList = fJobsList;
    }

    public int getFilterCase() {
        return filterCase;
    }

    public void setFilterCase(int filterCase) {
        this.filterCase = filterCase;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
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

    public List<JobsModel> getJobModelList() {
        return jobModelList;
    }

    public void setJobModelList(List<JobsModel> jobModelList) {
        this.jobModelList = jobModelList;
    }

	//@PostConstruct
    public void init(){
        //after constructor call
        HttpSession session = SessionUtils.getSession();
        Long userid= (Long) session.getAttribute("user_id");
        String userrole= (String) session.getAttribute("user_role");
        if(userrole.compareTo("provider") == 0){
            List<JobsModel> emptyJobModelList=new ArrayList<>();
            this.setJobModelList(emptyJobModelList);
            getJobsByProv(userid);
        }
//        if(userrole.compareTo("freelancer") == 0){
//            getAllOpenJobs();
//        }
    }
	
    public void getJobsByProv(Long userid){
        this.setJobsList(jobsSvcImpl.getJobsByProv(new Provider(userid)));
        //return jobsList;
        
         this.getJobsList().forEach((um) -> {
             String acceptedBy = "-";
             if(um.getAcceptedby() != null){
                 Users user = um.getAcceptedby().getUsers();
                 if(user == null){
                    user = usersSvcImpl.getUserById(um.getAcceptedby().getUid());
                 }
                 acceptedBy = user.getFirstname() + " " + user.getLastname();
             }
         JobsModel model=new JobsModel(String.valueOf(um.getJobid()), um.getTitle(), um.getSkills(), um.getDescription(), String.valueOf(um.getPayment()), um.getJobstatus(), String.valueOf(um.getCreatedby()), acceptedBy);
                if(um.getJobstatus().toUpperCase().contains("OPEN"))
                {
                   model.setIsDisable(false);
                   model.setIsComDisabled(true);
                }
                else if(um.getJobstatus().toUpperCase().contains("CLOSED")){
                   model.setIsComDisabled(false);
                   model.setIsDisable(true);
                }
                else{
                   model.setIsDisable(true);
                   model.setIsComDisabled(true);
                }
                getJobModelList().add(model);
                this.setJobModelList(this.getJobModelList());

                //else
                   // setDisable(false);

            });  
    }
    
    public void onClickJobId(String jobId)
    {//provider
        HttpSession session = SessionUtils.getSession();
        String userrole= (String) session.getAttribute("user_role");
        
        if(userrole.compareTo("provider") == 0){
            jobModelList.forEach((um) -> {
            if(um.getJobid().compareTo(jobId)==0)
            {
                um.setIsClicked(true);
            }
            });  
        }
           
    }
    
    public List<Jobs> displayAllJobs()
    {//for ADMIN
        HttpSession session = SessionUtils.getSession();
        String userrole= (String) session.getAttribute("user_role");
        
        if(userrole.compareTo("admin") == 0){
            jobsList = getJobsSvcImpl().getAllJobs();
        }
        
        return jobsList;
    }
    
    public String getRegisterButtonValue(long jobid){
        HttpSession session = SessionUtils.getSession();
        Long userid= (Long) session.getAttribute("user_id");
        for(Jobs job: fJobsList){
            if(job.getJobid() == jobid){
                if(job.getFreelancerList().stream().anyMatch(free -> Objects.equals(free.getUid(), userid))){
                    return "De-Register";
                }
                else{
                    return "Register";
                }
            }
        }
        return null;
    }
    
    public void toggleRegistrationForJob(long jobid){
        HttpSession session = SessionUtils.getSession();
        Long userid= (Long) session.getAttribute("user_id");

        jobsSvcImpl.toggleUserRegistrationForJob(jobid, userid);
    }
    
    public void refreshFJobsList(){
        List<Jobs> openJobsList = jobsSvcImpl.getAllOpenJobs();
        switch(filterCase){
            case 1:
                fJobsList = openJobsList.stream().filter(j -> Objects.equals(j.getJobid().toString(), searchStr)).collect(Collectors.toList());
                break;// ID
            case 2:
                fJobsList = openJobsList.stream().filter(j -> j.getSkills().toLowerCase().contains(searchStr.toLowerCase())).collect(Collectors.toList());
                break;// Key
            default: fJobsList = openJobsList; break;
        }
    }
    
    public void removeJob(long jobid){
        jobsSvcImpl.deleteJobsByJid(jobid);
    }
    
    public void completeJob(long jobid){
        jobsSvcImpl.completeJobAndPay(jobid);
    }
}
