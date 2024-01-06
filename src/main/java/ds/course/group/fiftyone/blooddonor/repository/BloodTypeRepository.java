package ds.course.group.fiftyone.blooddonor.repository;

import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.BloodTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path= "bloodtype")
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
    boolean findByBloodType(BloodTypes bloodType);
}
