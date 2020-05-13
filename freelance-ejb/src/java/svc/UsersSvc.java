package svc;
import entities.Freelancer;
import entities.Jobs;
import entities.Provider;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pratik
 */
@Remote
public interface UsersSvc {
    public List<Freelancer> fetchAllFreelancers();
    public List<Provider> getAllProviders();
    
    public void deleteFreelancerByID(Long fid);
    public void deleteProviderByID(Long pid);
    //public void deleteJobsByJid(Long jid);

    
}
