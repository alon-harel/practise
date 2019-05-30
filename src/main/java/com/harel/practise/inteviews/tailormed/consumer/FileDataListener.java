package com.harel.practise.inteviews.tailormed.consumer;

import com.harel.practise.inteviews.tailormed.consumer.converter.patient.HospitalPatientsConverterFactory;
import com.harel.practise.inteviews.tailormed.consumer.converter.patient.PatientConvertor;
import com.harel.practise.inteviews.tailormed.consumer.repository.patient.Patient;
import com.harel.practise.inteviews.tailormed.consumer.services.PatientService;
import com.harel.practise.inteviews.tailormed.queue.FilesQueue;
import com.harel.practise.inteviews.tailormed.reciever.FileData;

import java.io.File;
import java.util.List;

// This is my listener. If I use rabbit I will have routing key and will have several listeners working
// In parrallel.
// In my case a thread pulling from the queues:
public class FileDataListener implements Runnable {

    private final FilesQueue filesQueue;
    private final HospitalPatientsConverterFactory hospitalPatientsConverterFactory;
    private final PatientService patientService;

    public FileDataListener(FilesQueue filesQueue,
                            HospitalPatientsConverterFactory hospitalPatientsConverterFactory,
                            PatientService patientService) {
        this.filesQueue = filesQueue;
        this.hospitalPatientsConverterFactory = hospitalPatientsConverterFactory;
        this.patientService = patientService;
    }

    public void listenToPatientsData() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            final FileData fileData = filesQueue.pull();

            if (fileData != null) {
                if (fileData.getDataType().equals(FileData.DataType.PATIENT)) {
                    handlePatients(fileData.getFile());
                }
                else {
                    handleTreatments(fileData.getFile());
                }
            }

            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleTreatments(File file) {
        // Same functionality of patients will be applied to treatments
    }

    private void handlePatients(File file) {
        PatientConvertor patientConvertor = hospitalPatientsConverterFactory.get(file);
        final List<Patient> patients = patientConvertor.convertFrom(file);
        patientService.handle(patients);
    }
}
