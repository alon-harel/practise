package com.harel.practise.inteviews.tailormed.consumer.converter.treatment;

import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;
import com.harel.practise.inteviews.tailormed.consumer.repository.treatment.Treatment;

import java.io.File;
import java.util.List;

public interface TreatmentConverter {
    List<Treatment> convertFrom(File file);
}
