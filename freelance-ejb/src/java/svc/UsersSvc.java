package svc;
import entities.Freelancer;
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

    
}
