package com.harel.practise.inteviews.tailormed.consumer.converter.treatment;

import java.io.File;


public class HospitalPatientsConverterFactory {

    // This can be done with Spring autowired.
    private Hospital1TreatmentConverter hospital1PatientConverter = new Hospital1TreatmentConverter();
    private Hospital2PatientConverter hospital2PatientConverter = new Hospital2PatientConverter();

    public TreatmentConverter get(File file) {
        if (fileIsOfHospital1Format(file)) {
            return hospital1PatientConverter;
        }
        else if (fileIsOfHospital2Format(file)) {
            return hospital2PatientConverter;
        }
        else {
            throw new UnknowntPatientFileFormat();
        }

    }

    private boolean fileIsOfHospital2Format(File file) {
        return false;
    }

    private boolean fileIsOfHospital1Format(File file) {
        return false;
    }

    static class UnknowntPatientFileFormat extends RuntimeException {
        public UnknowntPatientFileFormat() {
            super("Unknown file format");
        }
    }


}
