package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.dto.CitizenDTO;
import ds.course.group.fiftyone.blooddonor.dto.CitizenResponseDto;
import ds.course.group.fiftyone.blooddonor.dto.EmailChangeDTO;
import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.service.CitizenService;
import ds.course.group.fiftyone.blooddonor.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen")
public class CitizenRestController {

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("")
    public List<Citizen> showCitizens() {
        return citizenService.getCitizens();
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("")
    public Citizen saveCitizen(@RequestBody Citizen citizen){
        return citizenService.saveCitizen(citizen);
    }

//    // old method
////    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
//    @GetMapping("/{id}")
//    public Citizen getCitizen(@PathVariable("id") Long id) {
//        return citizenService.getCitizen(id);
//    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("/user/{userId}")
    public CitizenResponseDto getCitizenByUserId(@PathVariable("userId") Long userId) {
        Long testUserId = userService.getCurrentUserId();
        return citizenService.getCitizenByUserId(testUserId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @DeleteMapping("/{id}")
    public void deleteCitizen(@PathVariable("id") Long id) {
        citizenService.deleteCitizen(id);
    }

    // new method to create a new citizen
    /*@PostMapping("/create")
    public ResponseEntity<Citizen> createCitizen(@RequestBody CitizenDTO citizenDTO) {
        try {
            Citizen newCitizen = new Citizen(citizenDTO.getFirstName(),
                    citizenDTO.getLastName(),
                    citizenDTO.getEmail(),
                    citizenDTO.getRegion(),
                    citizenDTO.isGoodHealth());

            Citizen createdCitizen = citizenService.createCitizenWithExistingBloodType(newCitizen, citizenDTO.getBloodTypeString());

            return new ResponseEntity<>(createdCitizen, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("/change-email")
    public ResponseEntity<Citizen> changeEmail(@RequestBody EmailChangeDTO email) {
        Long userId = userService.getCurrentUserId();
        citizenService.changeEmail(userId, email.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
