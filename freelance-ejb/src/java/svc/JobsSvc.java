
package svc;

import entities.Freelancer;
import entities.Jobapps;
import entities.Jobs;
import entities.Provider;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author OMEN
 */
@Remote
public interface JobsSvc {
    public boolean persist(Object obj);
    public List<Jobs> getAllJobs();
    public List<Jobs> getJobsByProv(Provider userid);
    public List<Jobapps> getFreelancersByJobId(Jobs userid);
    public void toggleUserRegistrationForJob(long jobid, long userid);
    public Freelancer getFreelancersById(Freelancer userid);
    public void deleteJobsByJid(Long jid);
    public boolean assignFreelancerToJobId(String jobid, String freelancerIDs, String jobStatus);


}
