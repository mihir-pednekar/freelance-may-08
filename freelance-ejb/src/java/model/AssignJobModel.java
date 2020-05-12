
package model;

public class AssignJobModel {
    
    private String jobid, title, description,jobstatus, freelanceID;

    public AssignJobModel(String jobid, String title, String description, String jobstatus, String freelanceID) {
        this.jobid = jobid;
        this.title = title;
        this.description = description;
        this.jobstatus = jobstatus;
        this.freelanceID = freelanceID;
    }

    public AssignJobModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getFreelanceID() {
        return freelanceID;
    }

    public void setFreelanceID(String freelanceID) {
        this.freelanceID = freelanceID;
    }
    
    
    
}
