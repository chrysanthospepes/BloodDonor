package ds.course.group.fiftyone.blooddonor.repository;

import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path= "bloodtype")
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
    boolean existsByBloodType(String bloodType);
    Optional<BloodType> findByBloodType(String bloodType);
}
