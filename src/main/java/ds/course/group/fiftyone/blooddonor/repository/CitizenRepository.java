package ds.course.group.fiftyone.blooddonor.repository;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(path= "citizen")
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Citizen findByUser_Id(Long userId);

    List<Citizen> findAllByIsDonorTrue();
}
