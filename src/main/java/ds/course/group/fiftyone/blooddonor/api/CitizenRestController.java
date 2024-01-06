package ds.course.group.fiftyone.blooddonor.api;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen")
public class CitizenRestController {

    @Autowired
    private CitizenRepository citizenRepository;

    @GetMapping("")
    public List<Citizen> getCitizens() {
        return citizenRepository.findAll();
    }

    @PostMapping("")
    public Citizen saveCitizen(@RequestBody Citizen citizen){
        return citizenRepository.saveCitizen(citizen);
    }

    @GetMapping("/{id}")
    public Citizen getCitizen(@PathVariable("id") Long id) {
        return citizenRepository.findById(id).get();
    }
}
