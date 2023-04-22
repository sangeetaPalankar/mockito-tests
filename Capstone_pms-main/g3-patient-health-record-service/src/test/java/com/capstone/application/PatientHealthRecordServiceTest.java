package com.capstone.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import kong.unirest.HttpRequestWithBody;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.ListCrudRepository;

import com.capstone.application.dto.VisitDetailsDto;
import com.capstone.application.exception.PatientHealthException;
import com.capstone.application.model.AdminInfo;
import com.capstone.application.model.NurseInfo;
import com.capstone.application.model.Patient;
import com.capstone.application.model.Prescription;
import com.capstone.application.model.Tests;
import com.capstone.application.model.VisitDetails;
import com.capstone.application.repository.AdminInfoRepo;
import com.capstone.application.repository.NurseInfoRepo;
import com.capstone.application.repository.PatientRepo;
import com.capstone.application.repository.PatietHealthRecordsRepository;
import com.capstone.application.repository.PrescriptionRepo;
import com.capstone.application.repository.TestRepo;
import com.capstone.application.service.impl.PatientHealthRecordsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kong.unirest.Unirest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientHealthRecordServiceTest {
	
	@InjectMocks
	private PatientHealthRecordsServiceImpl patientHealthService;
	
	@Mock 
	private AdminInfoRepo adminRepo;
	@Mock 
	private NurseInfoRepo nurseRepo;
	@Mock 
	private PatientRepo patientRepo;
	@Mock
	private PatietHealthRecordsRepository patientHealthRecordsRepo;
	@Mock 
	private PrescriptionRepo prescriptionrepo;
	@Mock 
	private TestRepo testrepo;
	
	@Mock
	private ModelMapper modelmapper;
	
	
//	@BeforeEach void setup()
//	{
//
//		this.patientHealthService=new PatientHealthRecordsServiceImpl();
//
//	}
	
	
	@Test
	void findByIdTest() {
		Optional<VisitDetails> v1 = Optional.ofNullable(new VisitDetails(1000, 12122, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts"));
		
		when(patientHealthRecordsRepo.findById(12122)).thenReturn(v1);
		when(patientHealthService.findById(12122)).thenReturn(v1);
		assertEquals(v1,patientHealthService.findById(12122));
	}
	
	@Test
	void findBloodGroupForPatient() throws PatientHealthException {
		Optional<VisitDetails> v1 = Optional.ofNullable(new VisitDetails(1000, 12122, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts"));
		
		when(patientHealthRecordsRepo.getBloodGroup(12122)).thenReturn(v1);
		when(patientHealthService.findBloodGroupForPatient(12122)).thenReturn(v1);
		assertEquals(v1,patientHealthService.findBloodGroupForPatient(12122));
	}
	
	@Test
	void getDetailsByAppId() {
		Optional<VisitDetails> v1 = Optional.ofNullable(new VisitDetails(1000, 12122, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts"));
		
		when(patientHealthRecordsRepo.getVisitDetailsBtAppId(1000)).thenReturn(v1);
		when(patientHealthService.getDetailsByAppId(1000)).thenReturn(v1);
		assertEquals(v1,patientHealthService.getDetailsByAppId(1000));
		
	}
		

	@Test
	void getPreviousVisitDetailsByPatientIdforhistory() {
		VisitDetails v1 = new VisitDetails(1001, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				50001, "normal", "normal", "peanuts");
		when(patientHealthRecordsRepo.getPreviousVisitIdDetailsById(111)).thenReturn(v1);
		when(patientHealthService.getPreviousVisitDetailsByPatientIdforhistory(111)).thenReturn(v1);
		assertEquals(v1,patientHealthService.getPreviousVisitDetailsByPatientIdforhistory(111));
	}
	
	@Test
	void findTestByVisitIdTest() {
		Tests t1 = new Tests(101, "Blood", "Normal", "normal", 10001);
		List<Tests> list = new ArrayList<>();
		list.add(t1);
		when(testrepo.findTestByVisitId(10001)).thenReturn(list);
		when(patientHealthService.findTestByVisitId(10001)).thenReturn(list);
		assertEquals(list,patientHealthService.findTestByVisitId(10001));
	}
	
	@Test
	void findVisitIdByPatientIdTest() {
		List<Integer> v2 = new ArrayList<>();
		v2.add(1000);
		when(patientHealthRecordsRepo.findVisitIdByPatientId(101)).thenReturn(v2);
		when(patientHealthService.findVisitIdByPatientId(101)).thenReturn(v2);
		assertEquals(v2,patientHealthService.findVisitIdByPatientId(101));
		
	}
	
	
	@Test
	public void findVisitDetailsByVisitId() {
		VisitDetails v1 = new VisitDetails(1001, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				50001, "normal", "normal", "peanuts");
		List<VisitDetails> list = new ArrayList<>();
		list.add(v1);

		when(patientHealthRecordsRepo.findVisitDetailsByVisitId(1001)).thenReturn(list);
		when(patientHealthService.findVisitDetailsByVisitId(1001)).thenReturn(list);
		assertEquals(list,patientHealthService.findVisitDetailsByVisitId(1001));
		
	}
	
	@Test
	void findPrescriptionByVisitId() {
		Prescription p1 = new Prescription(10001, "dolo", "1-0-1", "after lunch",101);
		Prescription p2 = new Prescription(10002, "abc", "1-0-1", "after lunch",102);
		List<Prescription> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		when(prescriptionrepo.findPrescriptionByVisitId(101)).thenReturn(list);
		when(patientHealthService.findPrescriptionByVisitId(101)).thenReturn(list);
		assertEquals(list,patientHealthService.findPrescriptionByVisitId(101));
		
	}
	
	@Test
	void findVisistDetailsByAppointmentId() {
		VisitDetails v1 = new VisitDetails(1001, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				50001, "normal", "normal", "peanuts");
		when(patientHealthRecordsRepo.findVisitDetailsById(50001)).thenReturn(v1);
		when(patientHealthService.findVisistDetailsByAppointmentId(50001)).thenReturn(v1);
		assertEquals(v1,patientHealthService.findVisistDetailsByAppointmentId(50001));
		
	}
	
	@Test
	void getPreviousVisitDetailsByPatientId() {
		VisitDetails v1 = new VisitDetails(1001, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				50001, "normal", "normal", "peanuts");
		when(patientHealthRecordsRepo.findVisitDetailsById(111)).thenReturn(v1);
		when(patientHealthService.getPreviousVisitDetailsByPatientId(111)).thenReturn(v1);
		assertEquals(v1,patientHealthService.getPreviousVisitDetailsByPatientId(111));
		
	}
	
	@Test
	void findPrescriptionById() {
		Prescription p1 = new Prescription(10001, "dolo", "1-0-1", "after lunch",101);
		Prescription p2 = new Prescription(10002, "abc", "1-0-1", "after lunch",102);
		List<Prescription> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		//when(prescriptionrepo.findPrescriptionByVisitId(101)).thenReturn(list);
		when(patientHealthService.findPrescriptionByVisitId(101)).thenReturn(list);
		assertEquals(list,patientHealthService.findPrescriptionById(101));
		
	}
	
	@Test
	void getNurseDetails() {
		NurseInfo n1 = new NurseInfo("nurse1@gmail.com","Riya", "Shetty");
		when(nurseRepo.getNurseInfoByEmail("nurse1@gmail.com")).thenReturn(n1);
		when(patientHealthService.getNurseDetails("nurse1@gmail.com")).thenReturn(n1);
		assertEquals(n1,patientHealthService.getNurseDetails("nurse1@gmail.com"));

	}
	
	
	
	@Test
	public void savePrescription() {
		Prescription p1 = new Prescription(10001, "dolo", "1-0-1", "after lunch",101);

//		Prescription savedEmployee = prescriptionrepo.save(p1);
//		assertThat(savedEmployee).isNotNull();
    	when(prescriptionrepo.save(p1)).thenReturn(p1);
		when(patientHealthService.savePrescription(p1)).thenReturn(p1);
		assertEquals(p1,patientHealthService.savePrescription(p1));

	}
	
	@Test
	public void saveTest() {
		Tests t1 = new Tests(101, "Blood", "Normal", "normal", 10001);
//		Prescription savedEmployee = testrepo.save(t1);
//		assertThat(savedEmployee).isNotNull();
    	when(testrepo.save(t1)).thenReturn(t1);
		when(patientHealthService.saveTest(t1)).thenReturn(t1);
		assertEquals(t1,patientHealthService.saveTest(t1));

	}
	
	
	@Test
	public void deleteTest() {
		
		doNothing().when(testrepo).deleteById(anyInt());
		patientHealthService.deleteTest(1);
		
	}
	
	@Test
	void deletePrescription() {
		
		doNothing().when(prescriptionrepo).deleteById(anyInt());
		patientHealthService.deletePrescription(10001);

	}
	
	@Test
	void deleteNurse() {
		
		doNothing().when(nurseRepo).deleteById(anyString());
		patientHealthService.deleteNurse("nurse1@gmail.com");

		
	}
	
	@Test
	void deleteAdmin() {
		//AdminInfo admin1 =new AdminInfo("admin1@gmail.com", "Aditi", "Sharma");
		//when(adminRepo.findById(anyString())).thenReturn(Optional.of(admin1));
		doNothing().when(adminRepo).deleteById(anyString());
		patientHealthService.deleteAdmin("admin1@gmail.com");

		
	}
	
	
	
	@Test
	void findAll() {
		VisitDetails v1 = new VisitDetails(1001, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts");
		VisitDetails v2 = new VisitDetails(1002, 111, 5, 58, 120, 78,
				35, 13, "B+", "nurse2@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts");
		
		
		List<VisitDetails> list = new ArrayList<VisitDetails>();
		list.add(v1);
		list.add(v2);
		when(patientHealthRecordsRepo.findAll()).thenReturn(list);
		assertEquals(list,patientHealthService.findAll());
		
	}
	
	
	

	
	@Test
	void update() {
		VisitDetails v1 = new VisitDetails(1000, 12122, 5, 58, 120, 78,
				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
				145, "normal", "normal", "peanuts");
		
		
		when(patientHealthRecordsRepo.save(v1)).thenReturn(v1);	
		assertEquals(patientHealthService.update(v1),v1);
		
		
	}
	
	@Test
	void updateforTestTest() {
		Tests t1 = new Tests(101, "Blood", "Normal", "normal", 10001);
		List<Tests>list=new ArrayList<>();
		list.add(t1);
		//when(testrepo.findAll()).thenReturn(list);
		when(testrepo.save(t1)).thenReturn(t1);	
		assertEquals(patientHealthService.updateforTest(t1),t1);
	}
	
	
	
	
	@Test
	void updatePrescriptionTest() {
		Prescription p1 = new Prescription(10001, "dolo", "1-0-1", "after lunch",101);
		
		when(prescriptionrepo.save(p1)).thenReturn(p1);	
		assertEquals(patientHealthService.updatePrescription(p1),p1);
		
	}
	
	@Test
	void updateForPrescriptionByPrescriptionId() {
		Optional<Prescription> p1 = Optional.of(new Prescription(10001, "dolo", "1-0-1", "after lunch",101));
		Prescription p2=new Prescription(10001, "dolo", "1-0-1", "after lunch",101);
		when(prescriptionrepo.findById(10001)).thenReturn(p1);
		when(prescriptionrepo.save(p2)).thenReturn(p2);	
		//when(patientHealthService.updateForPrescriptionByPrescriptionId(id,p1)).thenReturn(true);	
		assertEquals(patientHealthService.updateForPrescriptionByPrescriptionId(10001,p2),true);
		
		
	}
	
	@Test
	void updateForTestByTestId() {
		Optional<Tests> t1 = Optional.ofNullable(new Tests(101, "Blood", "Normal", "normal", 10001));
		List<Tests>list=new ArrayList<>();
		
		int testid= 101;
		when(testrepo.findById(101)).thenReturn(t1);
		Tests tr=new Tests(101, "Blood", "Normal", "normal", 10001);
//		tr=t1;
		when(testrepo.save(tr)).thenReturn(tr);	
		assertEquals(patientHealthService.updateForTestByTestId(testid,tr),tr);
		
	}
	
//	@Test
//	void updateVisitDetialsTest() {
//		VisitDetails v1 = new VisitDetails(1000, 12122, 5, 58, 120, 78,
//				35, 13, "B+", "nurse1@gmail.com", "physician1@gmail.com",
//				145, "normal", "normal", "peanuts");
//		
//		List<VisitDetails>list=new ArrayList<>();
//		list.add(v1);
//		when(patientHealthRecordsRepo.findAll()).thenReturn(list);
//		when(patientHealthRecordsRepo.save(v1)).thenReturn(v1);	
//		assertEquals(patientHealthService.updateVisitDetials(v1),v1);
//	}
	
	
	

	@Test
	void findAllPriscriptionTest() {
		
		Prescription p1 = new Prescription(10001, "dolo", "1-0-1", "after lunch",101);
		Prescription p2 = new Prescription(10002, "abc", "1-0-1", "after lunch",102);
		List<Prescription> list = new ArrayList<Prescription>();
		list.add(p1);
		list.add(p2);
		when(prescriptionrepo.findAll()).thenReturn(list);
		assertEquals(list,patientHealthService.findAllPriscription());

	}
	
	
	@Test
	void NursefindAllTest() {
		NurseInfo n1 = new NurseInfo("nurse1@gmail.com","Riya", "Shetty");
		NurseInfo n2 = new NurseInfo("nurse2@gmail.com","Jiya", "Shetty");

		List<NurseInfo> list = new ArrayList<NurseInfo>();
		list.add(n1);
		list.add(n2);
		when(nurseRepo.findAll()).thenReturn(list);
		assertEquals(list,patientHealthService.NursefindAll());
	}
	
	@Test
	void AdminfindAllTest() {
		AdminInfo a1= new AdminInfo("admin1@gmail.com","Darshan","Mane");
		AdminInfo a2= new AdminInfo("admin2@gmail.com","Ajay","Mane");
		List<AdminInfo> list = new ArrayList<AdminInfo>();
		list.add(a1);
		list.add(a2);
		
		when(adminRepo.findAll()).thenReturn(list);
		assertEquals(list,patientHealthService.AdminfindAll());
	}
	
	
	
	@Test
	public void countNurses()  {
		NurseInfo nurse1 =new NurseInfo("nurse1@gmail.com", "Aditi", "Sharma");
		NurseInfo nurse2 =new NurseInfo("nurse2@gmail.com", "Aryan", "Sharma");
		
		List<NurseInfo> nurseList = new ArrayList<NurseInfo>();
		nurseList.add(nurse1);
		nurseList.add(nurse2);
		
		when(nurseRepo.count()).thenReturn(2L);
		when(patientHealthService.countNurses()).thenReturn(2L);
		assertEquals(2,patientHealthService.countNurses());
	}

	
	@Test
	public void countAdmins()  {
		AdminInfo admin1 =new AdminInfo("admin1@gmail.com", "Aditi", "Sharma");
		AdminInfo admin2 =new AdminInfo("admin2@gmail.com", "Aryan", "Sharma");
		
		List<AdminInfo> adminList = new ArrayList<AdminInfo>();
		adminList.add(admin1);
		adminList.add(admin2);
		
		when(adminRepo.count()).thenReturn((long) adminList.size());
		when(patientHealthService.countAdmins()).thenReturn(2L);
		assertEquals((long) adminList.size(),patientHealthService.countAdmins());
	}
	
	@Test
	void findAdminInfoByEmailId() {
		Optional<AdminInfo> admin1 =Optional.ofNullable(new AdminInfo("admin1@gmail.com", "Aditi", "Sharma"));
		
		when(adminRepo.findById("admin1@gmail.com")).thenReturn(admin1);
		when(patientHealthService.findAdminInfoByEmailId("admin1@gmail.com")).thenReturn(admin1);
		assertEquals(admin1,patientHealthService.findAdminInfoByEmailId("admin1@gmail.com"));
		
	}
	

	
	

}