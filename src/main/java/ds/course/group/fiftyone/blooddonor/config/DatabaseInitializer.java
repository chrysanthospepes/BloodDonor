package ds.course.group.fiftyone.blooddonor.config;


import ds.course.group.fiftyone.blooddonor.entity.BloodType;
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
        bloodTypeRepository.findByBloodType("A+").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("A+"));
            return null;
        });
        bloodTypeRepository.findByBloodType("A-").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("A-"));
            return null;
        });
        bloodTypeRepository.findByBloodType("B+").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("B+"));
            return null;
        });
        bloodTypeRepository.findByBloodType("B-").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("B-"));
            return null;
        });
        bloodTypeRepository.findByBloodType("AB+").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("AB+"));
            return null;
        });
        bloodTypeRepository.findByBloodType("AB-").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("AB-"));
            return null;
        });
        bloodTypeRepository.findByBloodType("O+").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("O+"));
            return null;
        });
        bloodTypeRepository.findByBloodType("O-").orElseGet(() -> {
            bloodTypeRepository.save(new BloodType("O-"));
            return null;
        });

        // Add roles
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
