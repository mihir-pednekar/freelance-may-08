
package model;

public class AssignJobModel {
    
    private String jobid, title, description,jobstatus, freelanceID, freelanceName;

    public AssignJobModel() {
    }

    public AssignJobModel(String jobid, String title, String description, String jobstatus, String freelanceID, String freelanceName) {
        this.jobid = jobid;
        this.title = title;
        this.description = description;
        this.jobstatus = jobstatus;
        this.freelanceID = freelanceID;
        this.freelanceName = freelanceName;
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

    public String getFreelanceName() {
        return freelanceName;
    }

    public void setFreelanceName(String freelanceName) {
        this.freelanceName = freelanceName;
    }

    
}
