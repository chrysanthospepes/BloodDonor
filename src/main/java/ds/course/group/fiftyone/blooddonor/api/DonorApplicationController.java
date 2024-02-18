package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.dto.DonorApplicationDTO;
import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import ds.course.group.fiftyone.blooddonor.service.DonorApplicationService;
import ds.course.group.fiftyone.blooddonor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor-application")
public class DonorApplicationController {

    @Autowired
    private DonorApplicationService donorApplicationService;

    @Autowired
    private UserService userService;

    // Create application linked to user
    @Secured({"ROLE_USER"})
    @PostMapping("/create")
    public ResponseEntity<DonorApplication> createApplication(@RequestBody DonorApplicationDTO applicationDTO) {
        Long userId = userService.getCurrentUserId();
        DonorApplication createdApplication = donorApplicationService.createApplication(applicationDTO, userId);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    // Review application by applicationId (for admin/moderator)
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("/review/{applicationId}")
    public ResponseEntity<DonorApplication> reviewApplication(@PathVariable Long applicationId, @RequestParam boolean isAccepted) {
        DonorApplication reviewedApplication = donorApplicationService.reviewApplication(applicationId, isAccepted);
        return ResponseEntity.ok(reviewedApplication);
    }

    // Get all applications (for admin/moderator)
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("/unreviewed")
    public ResponseEntity<List<Long>> getPendingApplications() {
        List<Long> unreviewedApplications = donorApplicationService.getPendingApplications();
        return ResponseEntity.ok(unreviewedApplications);
    }

    // View application by applicationId (for admin/moderator)
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("/view/{applicationId}")
    public ResponseEntity<DonorApplication> viewApplication(@PathVariable Long applicationId) {
        DonorApplication application = donorApplicationService.viewApplication(applicationId);
        return ResponseEntity.ok(application);
    }

}

