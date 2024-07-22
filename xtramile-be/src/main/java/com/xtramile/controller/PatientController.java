package com.xtramile.controller;

import java.util.List;
import java.util.Optional;

import com.xtramile.util.ResponseData;
import com.xtramile.model.Patient;
import com.xtramile.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    @PostMapping("/patients")
    public ResponseEntity<ResponseData> createPatient(@RequestBody Patient patient) {

        patientRepository.save(patient);
        ResponseData responseData = new ResponseData(null,"Insert Ok.");
        return ResponseEntity.ok(responseData);
    }
    //get all patiens
    @GetMapping("/patients")
    public ResponseEntity<ResponseData> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        ResponseData responseData = new ResponseData(patients,"Get All Data Ok.");
        return ResponseEntity.ok(responseData);
    }

    // Get a patient by ID
    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    // Update a patient
    @PutMapping("/patients/{id}")
    public ResponseEntity<ResponseData> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        // Check if the patient exists
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            // Update the patient
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setFirstName(patient.getFirstName());
            updatedPatient.setLastName(patient.getLastName());
            updatedPatient.setPhoneNo(patient.getPhoneNo());
            updatedPatient.setDob(patient.getDob());
            updatedPatient.setPostCode(patient.getPostCode());
            updatedPatient.setAddress(patient.getAddress());
            updatedPatient.setState(patient.getState());
            updatedPatient.setSuburb(patient.getSuburb());
            // Update other fields as needed
            patientRepository.save(updatedPatient);
            ResponseData responseData = new ResponseData(null,"Update Ok.");
            return ResponseEntity.ok(responseData);
        } else {
            // Return a 404 Not Found response if the patient is not found
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a patient
    @DeleteMapping("/patients/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        ResponseData responseData = new ResponseData(null,"Delete Ok.");
        return ResponseEntity.ok(responseData);
    }
}
