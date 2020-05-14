package svc;

import constants.SqlQueryConstants;
import dao.PersistenceUnitConnec;
import entities.Freelancer;
import entities.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
public class UsersSvcImpl implements UsersSvc {

    private EntityManager em;

    @EJB
    private JobsSvcImpl jobsSvcImpl;
    private List<Freelancer> freelancersList = new ArrayList<>();
    private List<Provider> providersList = new ArrayList<>();

    @Override
    public List<Freelancer> fetchAllFreelancers() {
        //freelancersList
        try {
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_FREELANCERS);
            freelancersList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            System.gc();
        }
        return freelancersList;
    }

    @Override
    public List<Provider> getAllProviders() {
        //providersList=new ArrayList<>();
        try {
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_PROVIDERS);
            providersList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            System.gc();
        }
        return providersList;
    }

    @Override
    public void deleteFreelancerByID(Long fid) {
        try {
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            em.getTransaction().begin();
            Query query1 = em.createQuery(SqlQueryConstants.DELETE_USER_FROM_JOBAPPS);
            query1.setParameter("fid", new Freelancer(fid));
            int rows1 = query1.executeUpdate();
            Query query2 = em.createQuery(SqlQueryConstants.DELETE_FREELANCER_BY_ID);
            query2.setParameter("uid", fid);
            int rows2 = query2.executeUpdate();
            if (rows2 > 0) {
                Query query3 = em.createQuery(SqlQueryConstants.DELETE_USER_BY_ID);
                query3.setParameter("id", fid);
                int rows3 = query3.executeUpdate();
                em.getTransaction().commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            System.gc();
        }
    }
    
    public List<String> getAllUsernames(){
        List<String> usernames = null;
        try{
        em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
        em.getTransaction().begin();
        Query query = em.createQuery(SqlQueryConstants.FETCH_ALL_USERNAMES);
        usernames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            System.gc();
        }
        return usernames;
    }

    @Override
    public void deleteProviderByID(Long pid) {
        try {
            em = PersistenceUnitConnec.createEntityManager(SqlQueryConstants.PERSIST_UNIT);
            em.getTransaction().begin();
            Query query1 = em.createQuery(SqlQueryConstants.DELETE_JOBS_BY_PROVID);
            query1.setParameter("createdby", new Provider(pid));
            int rows1 = query1.executeUpdate();
            Query query2 = em.createQuery(SqlQueryConstants.DELETE_PROVIDER_BY_ID);
            query2.setParameter("pid", pid);
            int rows2 = query2.executeUpdate();
            Query query3 = em.createQuery(SqlQueryConstants.DELETE_USER_BY_ID);
            query3.setParameter("id", (pid));
            int rows3 = query3.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            System.gc();
        }
    }

}
