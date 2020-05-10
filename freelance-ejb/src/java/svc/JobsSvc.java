/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

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

}
