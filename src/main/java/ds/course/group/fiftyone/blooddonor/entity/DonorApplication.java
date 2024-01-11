package ds.course.group.fiftyone.blooddonor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "donor_applications")
public class DonorApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @Column
    @NotBlank
    private String region;

    @Column
    @NotNull
    private boolean goodHealth;

    @Column
    @NotBlank
    private String bloodType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private boolean isReviewed = false;

    @Column
    private boolean isAccepted = false;

    public DonorApplication() {
    }

    public DonorApplication(String firstName, String lastName, String email, String region, boolean goodHealth, String bloodType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.region = region;
        this.goodHealth = goodHealth;
        this.bloodType = bloodType;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
