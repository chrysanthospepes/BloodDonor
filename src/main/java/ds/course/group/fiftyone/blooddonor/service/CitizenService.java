package ds.course.group.fiftyone.blooddonor.service;

import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.repository.CitizenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Transactional
    public List<Citizen> getCitizens(){
        return citizenRepository.findAll();
    }

    @Transactional
    public Citizen saveCitizen(Citizen citizen){
        return citizenRepository.save(citizen);
    }

    @Transactional
    public Citizen getCitizen(Long citizenId) {
        return citizenRepository.findById(citizenId).get();
    }

    @Transactional
    public void deleteCitizen(Long citizenId){
        citizenRepository.deleteById(citizenId);
    }
}
