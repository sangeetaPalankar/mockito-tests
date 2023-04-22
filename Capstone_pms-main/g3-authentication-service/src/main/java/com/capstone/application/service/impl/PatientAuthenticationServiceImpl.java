package com.capstone.application.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.application.dto.PatientDto;
import com.capstone.application.model.Patient;
import com.capstone.application.repository.PatientAuthenticationRepository;
import com.capstone.application.service.PatientAuthenticationService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientAuthenticationServiceImpl implements PatientAuthenticationService {
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(PatientAuthenticationServiceImpl.class);

	@Autowired
	private ModelMapper modelmapper;

	private PatientAuthenticationRepository patientAuthenticationRepository;

	public PatientAuthenticationServiceImpl(PatientAuthenticationRepository patientAuthenticationRepository) {
		super();
		this.patientAuthenticationRepository = patientAuthenticationRepository;
	}

	@Override
	public Optional<Patient> patientLogin(String email, String password) {
		// TODO Auto-generated method stub
		
			return patientAuthenticationRepository.authenticateByEmailandPassword(email, password);
		
	}

	@Override
	public PatientDto createPatient(PatientDto patinetDto) {
		
			log.info("Patient registered successfully");
			Patient patient = modelmapper.map(patinetDto, Patient.class);
			Patient saveadPatient = patientAuthenticationRepository.save(patient);
			PatientDto savedPatientDto = modelmapper.map(saveadPatient, PatientDto.class);
			return savedPatientDto;
		
	}

	@Override
	public void updatepassword(String email, String password) {
		// TODO Auto-generated method stub
		patientAuthenticationRepository.updatePassword(email, password);
	}


@Override
	public boolean checkEmailAlreadyExist(String email) {
		// TODO Auto-generated method stub
	
	String exists=patientAuthenticationRepository.existEmail(email);
	if(exists==null) {
		return true;

	}else {
		return false;
	}
		
	}

}
