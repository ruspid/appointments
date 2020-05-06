package com.org.appointments.patientregistry;


import com.org.appointments.patientregistry.dto.PatientDto;
import com.org.appointments.patientregistry.dto.PatientRegistrationDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PatientService implements PatientFacade {

    private final PatientRepository patientRepository;

    @Override
    public String registerPatient(PatientRegistrationDto patientRegistrationForm) {
        return patientRepository.addPatientRecord(PatientCreator.from(patientRegistrationForm)).getId();
    }

    @Override
    public PatientDto searchPatient(String id) {
        return getPatientById(id).dto();
    }

    private Patient getPatientById(String id) {
        return patientRepository.searchPatient(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

    }

    @Override
    public void deletePatientRecord(String id) {
        patientRepository.deletePatientRecord(id);
    }

    @Override
    public PatientDto updatePatientRecord(PatientDto patientDto) {
        return patientRepository.updatePatientRecord(PatientCreator.from(patientDto))
                .dto();
    }

}
