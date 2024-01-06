package ds.course.group.fiftyone.blooddonor.dto;

public class CitizenDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private boolean goodHealth;
    private String bloodTypeString; // Identifier for BloodType

    // Constructors, Getters, and Setters

    public CitizenDTO() {
    }

    // Add additional constructors if needed

    // Getters and setters for each field
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isGoodHealth() {
        return goodHealth;
    }

    public void setGoodHealth(boolean goodHealth) {
        this.goodHealth = goodHealth;
    }

    public String getBloodTypeString() {
        return bloodTypeString;
    }

    public void setBloodTypeString(String bloodTypeString) {
        this.bloodTypeString = bloodTypeString;
    }
}
