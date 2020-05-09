/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package svc;

import entities.Jobs;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author OMEN
 */
@Remote
public interface JobsSvc {
    public List<Jobs> getAllJobs();
}
