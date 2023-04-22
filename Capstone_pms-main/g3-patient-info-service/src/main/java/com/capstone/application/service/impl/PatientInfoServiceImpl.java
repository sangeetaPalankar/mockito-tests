package com.capstone.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.application.dto.PatientDto;
import com.capstone.application.model.Patient;
import com.capstone.application.repository.PatientInfoRepository;
import com.capstone.application.service.PatientInfoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientInfoServiceImpl implements PatientInfoService {
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(PatientInfoServiceImpl.class);

	private PatientInfoRepository patientInfoRepository;

	@Autowired
	public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
		super();
		this.patientInfoRepository = patientInfoRepository;
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		
			log.info("Fetched patient list successfully");
			return patientInfoRepository.findAll();
		
	}

	@Override
	public Optional<Patient> findById(Integer patientId) {
		// TODO Auto-generated method stub
		
			log.info("Patient fetched by patientId");
			return patientInfoRepository.findById(patientId);
		
	}

	@Override
	public Patient update(Patient patient) {
		// TODO Auto-generated method stub
		
			log.info("Patient Updated successfully");
			Patient updateResponse = patientInfoRepository.save(patient);
			return updateResponse;
		
	}

	// Sangeeta
	@Override
	public long countPatient() {
		return patientInfoRepository.count();
	}

	// updateProfile
	@Override
	public PatientDto updatePatientById(int patientId, PatientDto patientDto) {
		Patient patient = patientInfoRepository.findById(patientId).get();
		patient.setAddress(patientDto.getAddress());
		patient.setContactNumber(patientDto.getContactNumber());

		patient.setTitle(patientDto.getTitle());
		patient.setDob(patientDto.getDob());
		patient.setGender(patientDto.getGender());

		patient.setFirstName(patientDto.getFirstName());

		patient.setLastName(patientDto.getLastName());

		patientInfoRepository.saveAndFlush(patient);

		return modelMapper.map(patient, PatientDto.class);
	}

}
