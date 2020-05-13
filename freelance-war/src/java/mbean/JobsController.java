/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import dao.UsersDao;
import entities.Freelancer;
import entities.Jobs;
import entities.Provider;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import svc.JobsSvcImpl;
import utils.SessionUtils;

/**
 *
 * @author Pratik
 */
@Named(value = "jobsController")
@SessionScoped
public class JobsController implements Serializable {

    @EJB
    private JobsSvcImpl jobsSvcImpl;
    
    private Long jobid;
    private String title, skills, description;
    private int payment;
    private String jobstatus;
    private Long createdby;
    private Long acceptedby;
     
    public JobsController() {
    }

    public JobsController(Long jobid, String title, String skills, String description, int payment, String jobstatus, Long createdby, Long acceptedby) {
        this.jobid = jobid;
        this.title = title;
        this.skills = skills;
        this.description = description;
        this.payment = payment;
        this.jobstatus = jobstatus;
        this.createdby = createdby;
        this.acceptedby = acceptedby;
    }

    public JobsSvcImpl getJobsSvcImpl() {
        return jobsSvcImpl;
    }

    public void setJobsSvcImpl(JobsSvcImpl jobsSvcImpl) {
        this.jobsSvcImpl = jobsSvcImpl;
    }

    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Long getAcceptedby() {
        return acceptedby;
    }

    public void setAcceptedby(Long acceptedby) {
        this.acceptedby = acceptedby;
    }

    public String getRegisterButtonValue(){
        return "Test";
    }
    
    
    public String addNewJob(){ 
        Timestamp tstamp = new Timestamp(System.currentTimeMillis());
        Long jobID=tstamp.getTime();
        HttpSession session = SessionUtils.getSession();
        String user=(String) session.getAttribute("username");
        Long createdby=(Long) session.getAttribute("user_id");
        Jobs jobObj = new Jobs(jobID, title, skills, description, payment, jobstatus,new Provider(createdby));
        jobsSvcImpl.persist(jobObj);
        return "providerJobs";
    } 
}
