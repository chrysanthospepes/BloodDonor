package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.entity.DonorApplication;
import ds.course.group.fiftyone.blooddonor.service.DonorApplicationService;
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

    @PostMapping("/submit")
    public ResponseEntity<DonorApplication> submitApplication(@RequestBody DonorApplication application) {
        DonorApplication submittedApplication = donorApplicationService.submitApplication(application);
        return new ResponseEntity<>(submittedApplication, HttpStatus.CREATED);
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
}
