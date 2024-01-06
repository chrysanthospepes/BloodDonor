package ds.course.group.fiftyone.blooddonor.config;


import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.BloodTypes;
import ds.course.group.fiftyone.blooddonor.repository.BloodTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @PostConstruct
    public void populateDatabase() {

        // Populate blood types
        for (BloodTypes bloodType : BloodTypes.values()) {
            if (!bloodTypeRepository.findByBloodType(bloodType)) {
                BloodType bloodTypeEntity = new BloodType(bloodType);
                bloodTypeRepository.save(bloodTypeEntity);
            }
        }
        
    }
}
