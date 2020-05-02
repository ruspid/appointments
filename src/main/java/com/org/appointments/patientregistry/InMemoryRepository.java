package com.org.appointments.patientregistry;

import com.org.appointments.patientregistry.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

class InMemoryRepository implements PatientRepository, PatientQueryRepository{

    private final Comparator<String> idComparator = Comparator.comparing(String::toString);
    private Map<String, Patient> patientRecords = Collections.synchronizedSortedMap(new TreeMap<>(idComparator));

    @Override
    public Patient addPatientRecord(Patient patient) {//here probably sort of validation should be made
        patient.setId(generateId());
        patientRecords.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public Optional<Patient> searchPatient(String id) {
        return Optional.ofNullable(patientRecords.get(id));
    }

    @Override
    public void deletePatientRecord(String id) {
        patientRecords.remove(id);
    }

    @Override
    public Patient updatePatientRecord(Patient patient) {
        return patientRecords.put(patient.getId(), patient);
    }

    @Override
    public Page<PatientDto> readAllPatients(Pageable pageable) {
        List<PatientDto> patientPage = patientRecords.values()
                .stream()
                .map(Patient::dto)
                .skip(computeNumberOfRecordsToBeSkipped(pageable))
                .limit(pageable.getOffset())
                .collect(Collectors.toList());
        return new PageImpl<>(patientPage, pageable, pageable.getOffset());
    }

    private long computeNumberOfRecordsToBeSkipped(Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();
    }


    private String generateId() {
        String id;
        do
            id = UUID.randomUUID().toString();
        while
        (patientRecords.containsKey(id));
        return id;
    }
}
