
package svc;

import constants.SqlQueryConstants;
import dao.PersistenceUnitConnec;
import entities.Freelancer;
import entities.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Pratik
 */
@Stateless
@LocalBean
public class UsersSvcImpl implements UsersSvc{
    
    private EntityManager em;
    private List<Freelancer> freelancersList=new ArrayList<>();
    private List<Provider> providersList=new ArrayList<>();


    @Override
    public List<Freelancer> fetchAllFreelancers(){
        //freelancersList
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_FREELANCERS);
            freelancersList = query.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(em != null){
                em.close();
            }
            System.gc();
        }
        return freelancersList;       
    }

    @Override
    public List<Provider> getAllProviders(){
        //providersList=new ArrayList<>();
        try{
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_PROVIDERS);
            providersList = query.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(em != null){
                em.close();
            }
            System.gc();
        }
       return providersList;
    }
}
