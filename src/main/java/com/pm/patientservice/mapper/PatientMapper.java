package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convert Entity -> Response DTO
    public static PatientResponseDTO toDTO(Patient patient) {
        if (patient == null) return null;

        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId() != null ? patient.getId().toString() : null);
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth() != null ? patient.getDateOfBirth().toString() : null);

        return patientDTO;
    }

    // Convert Request DTO -> Entity
    public static Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) return null;

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAddress(dto.getAddress());
        patient.setEmail(dto.getEmail());

        if (dto.getDateOfBirth() != null) {
            patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), DATE_FORMATTER));
        }
        patient.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
        return patient;
    }
}
