package ds.course.group.fiftyone.blooddonor.dto;

public class DonorApplicationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private boolean goodHealth;
    private String bloodType;

    public DonorApplicationDTO() {
    }

    public DonorApplicationDTO(String firstName, String lastName, String email, String region, boolean goodHealth, String bloodType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.region = region;
        this.goodHealth = goodHealth;
        this.bloodType = bloodType;
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

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
