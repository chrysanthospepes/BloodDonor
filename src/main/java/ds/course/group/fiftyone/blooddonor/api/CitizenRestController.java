package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.dto.CitizenDTO;
import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.service.CitizenService;
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

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("")
    public List<Citizen> showCitizens() {
        return citizenService.getCitizens();
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("")
    public Citizen saveCitizen(@RequestBody Citizen citizen){
        return citizenService.saveCitizen(citizen);
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @GetMapping("/{id}")
    public Citizen getCitizen(@PathVariable("id") Long id) {
        return citizenService.getCitizen(id);
    }

//    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @DeleteMapping("/{id}")
    public void deleteCitizen(@PathVariable("id") Long id) {
        citizenService.deleteCitizen(id);
    }

    @PostMapping("/create")
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
    }

}
