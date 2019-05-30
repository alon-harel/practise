package com.harel.practise.inteviews.tailormed.consumer.repository.patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// This class mimic the a real database repository
public class PatientRepository {
    private Map<String, Patient> patients = new HashMap<>();

    public Patient addPatient(Patient patient) {
        String id = UUID.randomUUID().toString();
        patients.put(id, Patient.from(id, patient));
        return patient;
    }

    public void updatePatient(Patient patient) {
        patients.put(patient.getId(), patient);
    }


    public Map<Patient, Patient> loadPatientsByExternalId(List<Patient> patients, String patientsHospital) {
        // Here we will load all patients from the database (in bulks) upon their external ids and
        // The hopspital (extrenal ids might be equal in two different hospitals).
        return null;
    }
}
