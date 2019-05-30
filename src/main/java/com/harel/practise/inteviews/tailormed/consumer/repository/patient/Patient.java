package com.harel.practise.inteviews.tailormed.consumer.repository.patient;

import java.util.Objects;

public class Patient {
    private String id;
    private String extrenalId;
    private Gender gender;
    private Sex sex;
    private String firstName;
    private String lastName;
    private boolean isDead;
    private long dateOfDeath;
    private long dateOfBirth;
    private String hospital;

    public static Patient from(String id, Patient patient) {
        //This is done with lombock and builder pattern:
        Patient newPatient = new Patient();
        newPatient.id = id;
        newPatient.sex = patient.sex;
        ///
        return newPatient;
    }

    public String getId() {
        return id;
    }

    public String getExtrenalId() {
        return extrenalId;
    }

    public String getHospital() {
        return hospital;
    }

    private enum Gender {
        MALE_TO_WOMAN,
        WOMEN_TO_MAN
    }

    private enum Sex {
        MALE,
        WOMAN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return isDead == patient.isDead &&
                dateOfDeath == patient.dateOfDeath &&
                dateOfBirth == patient.dateOfBirth &&
                Objects.equals(extrenalId, patient.extrenalId) &&
                gender == patient.gender &&
                sex == patient.sex &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(lastName, patient.lastName) &&
                Objects.equals(hospital, patient.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extrenalId, gender, sex, firstName, lastName, isDead, dateOfDeath, dateOfBirth, hospital);
    }

}
