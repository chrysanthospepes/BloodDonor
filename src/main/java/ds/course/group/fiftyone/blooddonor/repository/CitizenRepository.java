package ds.course.group.fiftyone.blooddonor.repository;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}
