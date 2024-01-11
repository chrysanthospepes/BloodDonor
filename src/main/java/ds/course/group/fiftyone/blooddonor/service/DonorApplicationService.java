package ds.course.group.fiftyone.blooddonor.service;

import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import ds.course.group.fiftyone.blooddonor.repository.BloodTypeRepository;
import ds.course.group.fiftyone.blooddonor.repository.CitizenRepository;
import ds.course.group.fiftyone.blooddonor.repository.DonorApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorApplicationService {

    @Autowired
    private DonorApplicationRepository donorApplicationRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    public DonorApplication submitApplication(DonorApplication donorApplication) {
        return donorApplicationRepository.save(donorApplication);
    }

    public DonorApplication reviewApplication(Long applicationId, boolean isAccepted) {
        DonorApplication application = donorApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
        application.setReviewed(true);
        application.setAccepted(isAccepted);
        donorApplicationRepository.save(application);

        if (isAccepted) {
            createCitizenFromApplication(application);
        }

        return application;
    }

    private void createCitizenFromApplication(DonorApplication application) {
        Citizen citizen = new Citizen();
        citizen.setFirstName(application.getFirstName());
        citizen.setLastName(application.getLastName());
        citizen.setEmail(application.getEmail());
        citizen.setRegion(application.getRegion());
        citizen.setGoodHealth(application.isGoodHealth());

        BloodType bloodType = bloodTypeRepository.findByBloodType(application.getBloodType())
                .orElseThrow(() -> new EntityNotFoundException("BloodType not found"));
        citizen.setBloodType(bloodType);


        citizenRepository.save(citizen);
    }

    public List<Long> getPendingApplications() {
        return donorApplicationRepository
                .findAllByIsReviewedFalse()
                .stream()
                .map(DonorApplication::getApplicationId)
                .collect(Collectors.toList());
    }

}
