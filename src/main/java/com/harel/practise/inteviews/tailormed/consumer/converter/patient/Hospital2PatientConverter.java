package com.harel.practise.inteviews.tailormed.consumer.converter.patient;

import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;

import java.io.File;
import java.util.List;

public class Hospital2PatientConverter implements PatientConvertor {
    @Override
    public List<Patient> convertFrom(File file) {
        // Convert upon the known csv format of hospital2
        return null;
    }
}
