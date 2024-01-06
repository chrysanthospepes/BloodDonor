package ds.course.group.fiftyone.blooddonor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bloodtypes")
public class BloodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodTypeId;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private BloodTypes bloodType;

    // Constructors
    public BloodType() {
    }

    public BloodType(BloodTypes bloodType) {
        this.bloodType = bloodType;
    }

    // Getters and Setters
    public Long getId() {
        return bloodTypeId;
    }

    public void setId(Long id) {
        this.bloodTypeId = id;
    }

    public BloodTypes getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodTypes bloodType) {
        this.bloodType = bloodType;
    }
}
