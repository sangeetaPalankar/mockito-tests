package com.capstone.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.capstone.application.dto.PatientDto;
import com.capstone.application.model.Patient;
import com.capstone.application.service.PatientInfoService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1")
@Log4j2
public class PatientInfoController {
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PatientInfoController.class);

	
	private PatientInfoService patientInfoService;

	public PatientInfoController(PatientInfoService patientInfoService) 
	{
		super();
		this.patientInfoService = patientInfoService;
	}

	
	@GetMapping("/patient/{patientId}")
	public Optional<Patient> allergyById(@PathVariable int patientId) 
	{
		
			log.info("fetching patient information by patientId successfully");
        Optional < Patient > optional = patientInfoService.findById(patientId);
		return optional;
		
	}
	
	@PostMapping("/patient/{patientId}")
	 public Patient creatingPatientInfo(@RequestBody Patient patient) {
		
			log.info("Added patient information successfully");
        Patient updateResponse = patientInfoService.update(patient);
        return updateResponse;
		
	}

	
	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> PatientList() {
		
			log.info("Patient list fetched successfully");
		List <Patient> patient = patientInfoService.findAll();
		return new ResponseEntity<>(patient, HttpStatus.OK );
		
	}
	
	//Sangeeta	
		@GetMapping("/patientCount")
		public long patientCount() {
		return patientInfoService.countPatient();
		}
	
		//updateprofile
		@PutMapping("/patient/{patientId}")
		public PatientDto putPatient(@PathVariable("patientId")int patientId,@RequestBody PatientDto patientDto){
			PatientDto patientChange=patientInfoService.updatePatientById(patientId, patientDto);
			System.out.println("title"+patientDto.getTitle());
			System.out.println("title"+patientDto.getDob()  );
			System.out.println("title"+patientDto.getGender()  );

			return patientChange; 
		}

}
