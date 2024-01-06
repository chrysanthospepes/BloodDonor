package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen")
public class CitizenRestController {

    @Autowired
    private CitizenService citizenService;

    @GetMapping("")
    public List<Citizen> showCitizens() {
        return citizenService.getCitizens();
    }

    @PostMapping("")
    public Citizen saveCitizen(@RequestBody Citizen citizen){
        return citizenService.saveCitizen(citizen);
    }

    @GetMapping("/{id}")
    public Citizen getCitizen(@PathVariable("id") Long id) {
        return citizenService.getCitizen(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCitizen(@PathVariable("id") Long id) {
        citizenService.deleteCitizen(id);
    }

}
