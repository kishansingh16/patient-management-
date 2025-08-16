package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    // Constructor Dependency Injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOs=patients.stream()
                .map(PatientMapper::toDTO).toList();// method reference, cleaner
              //.map(patient->PatientMapper.toDTO(patient)).toList();

        return patientResponseDTOs;

        //OR
        //return patients.stream()
        //                .map(PatientMapper::toDTO) // method reference, cleaner
        //                .collect(Collectors.toList()); // use toList() if Java 16+

    }
}
