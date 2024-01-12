package ds.course.group.fiftyone.blooddonor.service;

import ds.course.group.fiftyone.blooddonor.dto.DonorApplicationDTO;
import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import ds.course.group.fiftyone.blooddonor.entity.User;
import ds.course.group.fiftyone.blooddonor.repository.BloodTypeRepository;
import ds.course.group.fiftyone.blooddonor.repository.CitizenRepository;
import ds.course.group.fiftyone.blooddonor.repository.DonorApplicationRepository;
import ds.course.group.fiftyone.blooddonor.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public DonorApplication submitApplication(DonorApplication donorApplication) {
        return donorApplicationRepository.save(donorApplication);
    }

    public DonorApplication createApplication(DonorApplicationDTO applicationDTO, Long userId) {
        // Retrieve the user based on userId
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );

        // Check for existing application
        if (user.getDonorApplication() != null) {
            throw new IllegalStateException("User already has an application");
        }

        // Create and set the application
        DonorApplication application = convertToEntity(applicationDTO);
        application.setUser(user);
        return donorApplicationRepository.save(application);

    }

    private DonorApplication convertToEntity(DonorApplicationDTO donorApplicationDTO) {
        DonorApplication donorApplication = new DonorApplication();
        donorApplication.setFirstName(donorApplicationDTO.getFirstName());
        donorApplication.setLastName(donorApplicationDTO.getLastName());
        donorApplication.setEmail(donorApplicationDTO.getEmail());
        donorApplication.setRegion(donorApplicationDTO.getRegion());
        donorApplication.setBloodType(donorApplicationDTO.getBloodType());
        donorApplication.setGoodHealth(donorApplicationDTO.isGoodHealth());
        return donorApplication;
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
