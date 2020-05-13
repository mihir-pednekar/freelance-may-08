/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author mihir
 */
public class SqlQueryConstants {
    public static final String PERSIST_UNIT = "TestPersistence";
    public static final String FETCH_USERS = "SELECT u FROM Users u WHERE u.username = :username";
    public static final String FETCH_JOBS = "SELECT j FROM Jobs j WHERE j.jobid = :jobid";
    public static final String FETCH_PROVIDERS_BY_ID = "SELECT p FROM Provider p WHERE p.pid = :pid";
    public static final String FETCH_JOBS_BY_PROV = "SELECT j FROM Jobs j WHERE j.createdby = :createdby";
    public static final String FETCH_ALL_JOBS = "SELECT j FROM Jobs j";
    public static final String FETCH_JOBS_BY_STATUS = "SELECT j FROM Jobs j WHERE j.jobstatus = :jobstatus";
    
    public static final String FETCH_FREELANCERS_BY_JOBID = "SELECT a FROM Jobapps a WHERE a.jobid = :jobid";
    public static final String FETCH_ALL_PROVIDERS = "SELECT p FROM Provider p";
    public static final String FETCH_ALL_FREELANCERS = "SELECT f FROM Freelancer f";
    
    public static final String DELETE_PROVIDER_BY_ID="DELETE FROM Provider p WHERE p.pid = :pid";
    public static final String DELETE_FREELANCER_BY_ID="DELETE FROM Freelancer f WHERE f.uid = :uid";
    
    public static final String DELETE_USER_BY_ID="DELETE FROM Users u WHERE u.id = :id";
    
    public static final String DELETE_JOB_BY_JOBID="DELETE FROM Jobs j WHERE j.jobid = :jobid";
     public static final String DELETE_JOBS_BY_PROVID = "DELETE FROM Jobs j WHERE j.createdby = :createdby";

    public static final String DELETE_USER_FROM_JOBAPPS="DELETE FROM Jobapps j WHERE j.fid = :fid";

}
