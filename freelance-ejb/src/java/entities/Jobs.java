/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OMEN
 */
@Entity
@Table(name = "jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j")
    , @NamedQuery(name = "Jobs.findByJobid", query = "SELECT j FROM Jobs j WHERE j.jobid = :jobid")
    , @NamedQuery(name = "Jobs.findByTitle", query = "SELECT j FROM Jobs j WHERE j.title = :title")
    , @NamedQuery(name = "Jobs.findBySkills", query = "SELECT j FROM Jobs j WHERE j.skills = :skills")
    , @NamedQuery(name = "Jobs.findByDescription", query = "SELECT j FROM Jobs j WHERE j.description = :description")
    , @NamedQuery(name = "Jobs.findByPayment", query = "SELECT j FROM Jobs j WHERE j.payment = :payment")
    , @NamedQuery(name = "Jobs.findByJobstatus", query = "SELECT j FROM Jobs j WHERE j.jobstatus = :jobstatus")})
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "jobid")
    private Long jobid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "title")
    private String title;
    @Size(max = 30)
    @Column(name = "skills")
    private String skills;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment")
    private int payment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jobstatus")
    private String jobstatus;
    @JoinTable(name = "jobapps", joinColumns = {
        @JoinColumn(name = "jobid", referencedColumnName = "jobid")}, inverseJoinColumns = {
        @JoinColumn(name = "fid", referencedColumnName = "uid")})
    @ManyToMany
    private List<Freelancer> freelancerList;
    @JoinColumn(name = "createdby", referencedColumnName = "pid")
    @ManyToOne
    private Provider createdby;
    @JoinColumn(name = "acceptedby", referencedColumnName = "uid")
    @ManyToOne
    private Freelancer acceptedby;

    public Jobs() {
    }

    public Jobs(Long jobid) {
        this.jobid = jobid;
    }

    public Jobs(Long jobid, String title, String description, int payment, String jobstatus) {
        this.jobid = jobid;
        this.title = title;
        this.description = description;
        this.payment = payment;
        this.jobstatus = jobstatus;
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

    @XmlTransient
    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }

    public void setFreelancerList(List<Freelancer> freelancerList) {
        this.freelancerList = freelancerList;
    }

    public Provider getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Provider createdby) {
        this.createdby = createdby;
    }

    public Freelancer getAcceptedby() {
        return acceptedby;
    }

    public void setAcceptedby(Freelancer acceptedby) {
        this.acceptedby = acceptedby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobid != null ? jobid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.jobid == null && other.jobid != null) || (this.jobid != null && !this.jobid.equals(other.jobid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Jobs[ jobid=" + jobid + " ]";
    }
    
}
