/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pratik
 */
@Entity
@Table(name = "jobapps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobapps.findAll", query = "SELECT j FROM Jobapps j")
    , @NamedQuery(name = "Jobapps.findByAid", query = "SELECT j FROM Jobapps j WHERE j.aid = :aid")})
public class Jobapps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "aid")
    private Long aid;
    @JoinColumn(name = "jobid", referencedColumnName = "jobid")
    @ManyToOne(optional = false)
    private Jobs jobid;
    @JoinColumn(name = "fid", referencedColumnName = "uid")
    @ManyToOne(optional = false)
    private Freelancer fid;

    public Jobapps() {
    }

    public Jobapps(Long aid) {
        this.aid = aid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Jobs getJobid() {
        return jobid;
    }

    public void setJobid(Jobs jobid) {
        this.jobid = jobid;
    }

    public Freelancer getFid() {
        return fid;
    }

    public void setFid(Freelancer fid) {
        this.fid = fid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobapps)) {
            return false;
        }
        Jobapps other = (Jobapps) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Jobapps[ aid=" + aid + " ]";
    }
    
}
