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
    public static final String FETCH_ALL_JOBS = "SELECT * FROM Jobs";
    public static final String FETCH_JOBS_BY_STATUS = "SELECT j FROM Jobs j WHERE j.jobstatus = :jobstatus";
}
