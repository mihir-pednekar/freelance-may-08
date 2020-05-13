package mbean;
import entities.Freelancer;
import entities.Provider;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import model.FreelancerModel;
import model.ProviderModel;
import svc.JobsSvcImpl;
import svc.UsersSvcImpl;

@Named(value = "ViewUsersCont")
@RequestScoped
@ManagedBean
public class ViewUsersController 
{
    @EJB
    private JobsSvcImpl jobsSvcImpl;
    
    @EJB
    private UsersSvcImpl allUsersSvcImpl;
    private List<Freelancer> freelancerList=new ArrayList<>();
    private List<FreelancerModel> freelancerModelList=new ArrayList<>();
    private List<Provider> providerList=new ArrayList<>();
    private List<ProviderModel> providerModelList=new ArrayList<>();


    
    public ViewUsersController()
    {
        
    }

    public JobsSvcImpl getJobsSvcImpl() {
        return jobsSvcImpl;
    }

    public void setJobsSvcImpl(JobsSvcImpl jobsSvcImpl) {
        this.jobsSvcImpl = jobsSvcImpl;
    }

    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }

    public List<ProviderModel> getProviderModelList() {
        return providerModelList;
    }

    public void setProviderModelList(List<ProviderModel> providerModelList) {
        this.providerModelList = providerModelList;
    }

    public void setFreelancerList(List<Freelancer> freelancerList) {
        this.freelancerList = freelancerList;
    }

    public List<FreelancerModel> getFreelancerModelList() {
        return freelancerModelList;
    }

    public void setFreelancerModelList(List<FreelancerModel> freelancerModelList) {
        this.freelancerModelList = freelancerModelList;
    }
    
    @PostConstruct
    public void init(){
        getAllFreelancers();
        getAllProviders();
        //HttpSession session = SessionUtils.getSession();
         //  Long userid= (Long) session.getAttribute("user_id");
        //  freelancerList = jobsSvcImpl.getFreelancersByJobId());
        
        //this.setJobsList(jobsList);
        //return jobsList;
        
        // freelancerList.forEach((um) -> {
        // FreelancerModel model=new FreelancerModel(String.valueOf(um.getUid()), um.getSkills(), um.getMessage(), um.getUsers().getFirstname(), um.getUsers().getLastname(), um.getUsers().getUserRole(), um.getUsers().getUsername());
                

                //else
                   // setDisable(false);

           // });  
    }
    
    public List<FreelancerModel> getAllFreelancers()
    {  
       freelancerModelList=new ArrayList<>();
       List<Freelancer> temp= allUsersSvcImpl.fetchAllFreelancers();
        //setFreelancerList(temp);
        temp.forEach((f)->{ 
             FreelancerModel model=new FreelancerModel(String.valueOf(f.getUid()), f.getSkills(), f.getMessage(), f.getUsers().getFirstname(), f.getUsers().getLastname(), f.getUsers().getUserRole(), f.getUsers().getUsername());
             this.getFreelancerModelList().add(model); 
        });
        this.setFreelancerModelList(this.getFreelancerModelList());
 
        return freelancerModelList;
    }
    
    
    public List<ProviderModel> getAllProviders()
    {
       providerModelList=new ArrayList<>();
       List<Provider> temp =allUsersSvcImpl.getAllProviders();
        temp.forEach((p)->{
        ProviderModel pModel=new ProviderModel(String.valueOf(p.getUsers().getId()),p.getUsers().getFirstname(),p.getUsers().getLastname(),p.getUsers().getUsername(),p.getUsers().getUserRole(),String.valueOf(p.getUsers().getRegDate()),String.valueOf(p.getAmount()));
        
        if(!providerModelList.contains(pModel))
        {
            providerModelList.add(pModel); 
        }
        });
       return providerModelList;
    }

}
