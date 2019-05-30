package com.harel.practise.inteviews.tailormed.consumer.services;

import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;
import com.harel.practise.inteviews.tailormed.consumer.repository.patient.PatientRepository;

import java.util.List;
import java.util.Map;

public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void handle(Patient patient, Map<Patient, Patient> dbPatients) {
        if (!dbPatients.containsKey(patient)) {
            // New patient that is not in the database:
            patientRepository.addPatient(patient);
        }
        else {
            Patient dbPatient = dbPatients.get(patient);
            if (!dbPatient.equals(patient)) {
                // Update the patient in the database:
                patientRepository.updatePatient(patient);
            }
        }
    }

    public void handle(List<Patient> patients) {
        // All patients are from the same hospital, so we will pick the hospital of the first one.
        String patientsHospital = patients.get(0).getHospital();
        Map<Patient, Patient> dbPatientsMap =
                patientRepository.loadPatientsByExternalId(patients, patientsHospital);

        patients.forEach(patient -> {
            handle(patient, dbPatientsMap);
        });
    }
}
