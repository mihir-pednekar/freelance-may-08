/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "freelancer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freelancer.findAll", query = "SELECT f FROM Freelancer f")
    , @NamedQuery(name = "Freelancer.findByUid", query = "SELECT f FROM Freelancer f WHERE f.uid = :uid")
    , @NamedQuery(name = "Freelancer.findBySkills", query = "SELECT f FROM Freelancer f WHERE f.skills = :skills")
    , @NamedQuery(name = "Freelancer.findByMessage", query = "SELECT f FROM Freelancer f WHERE f.message = :message")})
public class Freelancer implements Serializable {

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fid")
    private List<Jobapps> jobappsList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "uid")
    private Long uid;
    @Size(max = 500)
    @Column(name = "skills")
    private String skills;
    @Size(max = 500)
    @Column(name = "message")
    private String message;
    @ManyToMany(mappedBy = "freelancerList")
    private List<Jobs> jobsList;
    @OneToMany(mappedBy = "acceptedby")
    private List<Jobs> jobsList1;
    @JoinColumn(name = "uid", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Freelancer() {
    }

    public Freelancer(Long uid) {
        this.uid = uid;
    }

    public Freelancer(Long uid, String skills, String message) {
        this.uid = uid;
        this.skills = skills;
        this.message = message;
    }
    
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlTransient
    public List<Jobs> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }

    @XmlTransient
    public List<Jobs> getJobsList1() {
        return jobsList1;
    }

    public void setJobsList1(List<Jobs> jobsList1) {
        this.jobsList1 = jobsList1;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Freelancer)) {
            return false;
        }
        Freelancer other = (Freelancer) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Freelancer[ uid=" + uid + " ]";
    }

    @XmlTransient
    public List<Jobapps> getJobappsList() {
        return jobappsList;
    }

    public void setJobappsList(List<Jobapps> jobappsList) {
        this.jobappsList = jobappsList;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
}
