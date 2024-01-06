package ds.course.group.fiftyone.blooddonor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class BloodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodTypeId;

    @Column
    @NotBlank
    private String bloodType;

    // Constructors
    public BloodType() {
    }

    public BloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    // Getters and Setters
    public Long getId() {
        return bloodTypeId;
    }

    public void setId(Long id) {
        this.bloodTypeId = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
