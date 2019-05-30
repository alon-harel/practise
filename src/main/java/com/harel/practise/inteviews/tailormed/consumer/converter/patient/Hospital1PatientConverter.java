package com.harel.practise.inteviews.tailormed.consumer.converter.patient;

import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Hospital1PatientConverter implements PatientConvertor {
    @Override
    public List<Patient> convertFrom(File file) {
        // Convert upon the known csv format of hospital1
        return null;
    }
}
