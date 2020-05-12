/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import svc.JobsSvcImpl;
import entities.Jobs;
import java.util.List;

/**
 *
 * @author OMEN
 */
@Named(value = "jobsDisp")
@SessionScoped
public class JobsDisplayController implements Serializable {

    /**
     * Creates a new instance of JobsDisplayController
     */
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private List<Jobs> jobsList;
    private String searchStr, keyStr;

    public String getSearchStr() {
        return searchStr;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }
    
    
    public JobsDisplayController() {
        jobsSvcImpl = new JobsSvcImpl();
        getAllOpenJobs();
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
        jobsList = jobsSvcImpl.getAllJobs();
    }
    
    public void getAllOpenJobs(){
        jobsList = jobsSvcImpl.getAllOpenJobs();
    }
    
    public void applySearch(){
        
    }
    
    public void applyKeyFilter(){
        
    }
    
}
