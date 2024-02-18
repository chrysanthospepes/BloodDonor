package ds.course.group.fiftyone.blooddonor.service;

import ds.course.group.fiftyone.blooddonor.dto.CitizenResponseDto;
import ds.course.group.fiftyone.blooddonor.entity.BloodType;
import ds.course.group.fiftyone.blooddonor.entity.Citizen;
import ds.course.group.fiftyone.blooddonor.repository.BloodTypeRepository;
import ds.course.group.fiftyone.blooddonor.repository.CitizenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Transactional
    public List<Citizen> getCitizens(){
        return citizenRepository.findAll();
    }

    @Transactional
    public Citizen saveCitizen(Citizen citizen){
        return citizenRepository.save(citizen);
    }

    public Citizen createCitizenWithExistingBloodType(Citizen newCitizen, String bloodTypeString) {
        // Fetch the existing BloodType entity
        BloodType bloodType = bloodTypeRepository.findByBloodType(bloodTypeString)
                .orElseThrow(() -> new EntityNotFoundException("BloodType not found"));

        // Set the BloodType in the new Citizen
        newCitizen.setBloodType(bloodType);

        // Save the new Citizen
        return citizenRepository.save(newCitizen);
    }

    @Transactional
    public Citizen getCitizen(Long citizenId) {
        return citizenRepository.findById(citizenId).get();
    }

    @Transactional
    public CitizenResponseDto getCitizenByUserId(Long userId) {
        Citizen citizen = citizenRepository.findByUser_Id(userId);
        if (citizen == null) {
            throw new EntityNotFoundException("Citizen not found");
        }

        CitizenResponseDto citizenResponseDto = new CitizenResponseDto();
        citizenResponseDto.setCitizenId(citizen.getCitizenId());
        citizenResponseDto.setFirstName(citizen.getFirstName());
        citizenResponseDto.setLastName(citizen.getLastName());
        citizenResponseDto.setEmail(citizen.getEmail());
        citizenResponseDto.setRegion(citizen.getRegion());
        citizenResponseDto.setGoodHealth(citizen.isGoodHealth());
        citizenResponseDto.setBloodTypeString(citizen.getBloodType().getBloodType());
        citizenResponseDto.setDonor(citizen.isDonor());

        return citizenResponseDto;
    }

    @Transactional
    public void deleteCitizen(Long citizenId){
        citizenRepository.deleteById(citizenId);
    }

    @Transactional
    public void changeEmail(Long userId, String email){
        Citizen citizen = citizenRepository.findByUser_Id(userId);
        citizen.setEmail(email);
        //citizenRepository.delete(citizen);
        citizenRepository.save(citizen);
    }

    public List<Citizen> getDonors() {
        return citizenRepository.findAllByIsDonorTrue();
    }

}
