package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.dto.DonorApplicationDTO;
import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import ds.course.group.fiftyone.blooddonor.service.DonorApplicationService;
import ds.course.group.fiftyone.blooddonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor-application")
public class DonorApplicationController {

    @Autowired
    private DonorApplicationService donorApplicationService;

    @Autowired
    private UserService userService;

    // old method
    @PostMapping("/submit")
    public ResponseEntity<DonorApplication> submitApplication(@RequestBody DonorApplication application) {
        DonorApplication submittedApplication = donorApplicationService.submitApplication(application);
        return new ResponseEntity<>(submittedApplication, HttpStatus.CREATED);
    }

    // we use this method to create a new application
    @PostMapping("/create")
    public ResponseEntity<DonorApplication> createApplication(@RequestBody DonorApplicationDTO applicationDTO) {
        Long userId = userService.getCurrentUserId();
        DonorApplication createdApplication = donorApplicationService.createApplication(applicationDTO, userId);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @PostMapping("/review/{applicationId}")
    public ResponseEntity<DonorApplication> reviewApplication(@PathVariable Long applicationId, @RequestParam boolean isAccepted) {
        DonorApplication reviewedApplication = donorApplicationService.reviewApplication(applicationId, isAccepted);
        return ResponseEntity.ok(reviewedApplication);
    }

    @GetMapping("/unreviewed")
    public ResponseEntity<List<Long>> getPendingApplications() {
        List<Long> unreviewedApplications = donorApplicationService.getPendingApplications();
        return ResponseEntity.ok(unreviewedApplications);
    }

    // View application by applicationId (for admin/moderator)
    @GetMapping("/view/{applicationId}")
    public ResponseEntity<DonorApplication> viewApplication(@PathVariable Long applicationId) {
        DonorApplication application = donorApplicationService.viewApplication(applicationId);
        return ResponseEntity.ok(application);
    }

}

