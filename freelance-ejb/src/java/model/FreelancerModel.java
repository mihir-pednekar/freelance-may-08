
package model;


public class FreelancerModel extends UsersModel{
    private String Skills;
    private String id, message,  firstName, lastName,role,username;

    public FreelancerModel(String id, String Skills, String message, String firstName, String lastName,String role, String username) {
        this.id = id;
        this.Skills = Skills;
        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role=role;
        this.username=username;
    }

    public FreelancerModel() {
    }
    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getSkills() {
        return Skills;
    }

    public void setSkills(String Skills) {
        this.Skills = Skills;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
