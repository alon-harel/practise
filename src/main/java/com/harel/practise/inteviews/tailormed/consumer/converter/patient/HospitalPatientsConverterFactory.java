package com.harel.practise.inteviews.tailormed.consumer.converter.patient;

import java.io.File;

public class HospitalPatientsConverterFactory {

    // This can be done with Spring autowired.
    private Hospital1PatientConverter hospital1PatientConverter = new Hospital1PatientConverter();
    private Hospital2PatientConverter hospital2PatientConverter = new Hospital2PatientConverter();

    public PatientConvertor get(File file) {
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
