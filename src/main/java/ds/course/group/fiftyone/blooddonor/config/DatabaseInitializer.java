package ds.course.group.fiftyone.blooddonor.config;


import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.BloodTypes;
import ds.course.group.fiftyone.blooddonor.entity.Role;
import ds.course.group.fiftyone.blooddonor.repository.BloodTypeRepository;
import ds.course.group.fiftyone.blooddonor.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void populateDatabase() {

        // Populate blood types
        for (BloodTypes bloodType : BloodTypes.values()) {
            if (!bloodTypeRepository.findByBloodType(bloodType)) {
                BloodType bloodTypeEntity = new BloodType(bloodType);
                bloodTypeRepository.save(bloodTypeEntity);
            }
        }

        //Add roles
        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_ADMIN"));
            return null;
        });
        roleRepository.findByName("ROLE_MODERATOR").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_MODERATOR"));
            return null;
        });
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_USER"));
            return null;
        });
        
    }
}
