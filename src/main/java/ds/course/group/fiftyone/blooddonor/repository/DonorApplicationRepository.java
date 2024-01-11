package ds.course.group.fiftyone.blooddonor.repository;

import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path= "donorapplication")
public interface DonorApplicationRepository extends JpaRepository<DonorApplication, Long> {
    List<DonorApplication> findAllByIsReviewedFalse();
}
