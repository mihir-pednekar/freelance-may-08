
package model;


public class FreelancerModel extends UsersModel{
    private String Skills;
    private String id, message,  firstName, lastName;

    public FreelancerModel(String id, String Skills, String message, String firstName, String lastName) {
        this.id = id;
        this.Skills = Skills;
        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
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
