package com.harel.practise.inteviews.tailormed.reciever;

import java.io.File;

public class FileData {
    private File file;
    private DataType dataType;

    private FileData(File file, DataType dataType) {
        this.file = file;
        this.dataType = dataType;
    }

    public static FileData createPatient(File file) {
        return new FileData(file, DataType.PATIENT);
    }

    public static FileData createTreatment(File file) {
        return new FileData(file, DataType.TREATMENT);
    }


    public File getFile() {
        return file;
    }

    public DataType getDataType() {
        return dataType;
    }

    public enum DataType {
        PATIENT,
        TREATMENT
    }
}
