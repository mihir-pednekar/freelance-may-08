package model;
public class JobsModel {
    private String jobid, title, skills, description, payment,jobstatus,createdby, acceptedBy;
    private boolean isDisable=false, isClicked=false, isComDisabled = true;

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public boolean getIsComDisabled() {
        return isComDisabled;
    }

    public void setIsComDisabled(boolean isComDisabled) {
        this.isComDisabled = isComDisabled;
    }
    
    public boolean isIsDisable() {
        return isDisable;
    }

    public boolean isIsClicked() {
        return isClicked;
    }

    public void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }
    

    public void setIsDisable(boolean isDisable) {
        this.isDisable = isDisable;
    }

    public JobsModel() {
    }

    public JobsModel(String jobid, String title, String skills, String description, String payment, String jobstatus, String createdby, String acceptedBy) {
        this.jobid = jobid;
        this.title = title;
        this.skills = skills;
        this.description = description;
        this.payment = payment;
        this.jobstatus = jobstatus;
        this.createdby = createdby;
        this.acceptedBy = acceptedBy;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }   
}
