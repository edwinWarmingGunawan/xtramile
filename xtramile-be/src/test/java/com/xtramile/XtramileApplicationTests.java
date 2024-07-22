package com.xtramile;

import com.xtramile.model.Patient;
import com.xtramile.repository.PatientRepository;
import com.xtramile.util.ResponseData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRepository patientRepository;

	@Test
	void createPatient() throws Exception {
		// Given
		Patient patient = new Patient("John", "Doe", new Date(1234567890), "1234567890", "1234", "Address", "State", "Suburb");
		when(patientRepository.save(patient)).thenReturn(patient);

		// When
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/patients")
						.contentType("application/json")
						.content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"phoneNo\":\"1234567890\",\"dob\":\"1990-01-01\",\"postCode\":\"1234\",\"address\":\"Address\",\"state\":\"State\",\"suburb\":\"Suburb\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		ResponseEntity<ResponseData> response = new ResponseEntity<>(new ResponseData(null, "Insert Ok."), HttpStatus.OK);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void getAllPatients() throws Exception {
		// Given
		List<Patient> patients = Arrays.asList(new Patient("John", "Doe", new Date(1234567890), "1990-01-01", "1234", "Address", "State", "Suburb"));
		when(patientRepository.findAll()).thenReturn(patients);

		// When
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		ResponseEntity<ResponseData> response = new ResponseEntity<>(new ResponseData(patients, "Get All Data Ok."), HttpStatus.OK);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void getPatientById() throws Exception {
		// Given
		Patient patient = new Patient("John", "Doe", new Date(1234567890), "1990-01-01", "1234", "Address", "State", "Suburb");
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

		// When
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/patients/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		assertEquals(patient, patientRepository.findById(1L).get());
	}

	@Test
	void updatePatient() throws Exception {
		// Given
		Patient patient = new Patient("John", "Doe", new Date(1234567890), "1990-01-01", "1234", "Address", "State", "Suburb");
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

		// When
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/patients/1")
						.contentType("application/json")
						.content("{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"phoneNo\":\"1234567890\",\"dob\":\"1990-01-01\",\"postCode\":\"1234\",\"address\":\"Address\",\"state\":\"State\",\"suburb\":\"Suburb\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		ResponseEntity<ResponseData> response = new ResponseEntity<>(new ResponseData(null, "Update Ok."), HttpStatus.OK);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void deletePatient() throws Exception {
		// When
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/patients/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		ResponseEntity<ResponseData> response = new ResponseEntity<>(new ResponseData(null, "Delete Ok."), HttpStatus.OK);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}