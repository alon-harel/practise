package com.harel.practise.inteviews.tailormed.consumer.converter.patient;

import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;

import java.io.File;
import java.util.List;

public interface PatientConvertor {

    List<Patient> convertFrom(File file);
}
