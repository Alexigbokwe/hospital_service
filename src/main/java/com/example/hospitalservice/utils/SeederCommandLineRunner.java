package com.example.hospitalservice.utils;

import java.util.Random;

import com.example.hospitalservice.entities.Patient;
import com.example.hospitalservice.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
class SeederCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    private static Logger LOG = LoggerFactory
            .getLogger(SeederCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {

        int numberOfRows = 150;
        int counter = 0;
        int min = 1;
        int max = 70;
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        while (counter <= numberOfRows) {
            String randomName = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            int randomAge = (int) Math.floor(Math.random() * (max - min + 1) + min);

            LOG.info("args[{}]: {}", counter, randomName);
            Patient patient = new Patient();

            patient.setName(randomName);
            patient.setAge(randomAge);

            patientRepository.save(patient);
            counter++;
        }
    }
}