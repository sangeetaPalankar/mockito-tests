package com.capstone.application;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capstone.application.controller.PatientHealthRecordController;
import com.capstone.application.dto.VisitDetailsDto;
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
//import com.capstone.application.service.impl.PatientHealthRecordsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.capstone.application.service.PatientHealthRecordService;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PatientHealthRecordServiceApplicationTests {
		
	@InjectMocks
	PatientHealthRecordController patientHealthRecordController;

	
	@MockBean
	private PatientHealthRecordService patientHealthRecordService;
	
	
	
	
	
	 @Autowired
	  private WebApplicationContext webApplicationContext;
	  private MockMvc mockMvc;

	  
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private VisitDetails vD1;
	private VisitDetails vD2;
	
	private Prescription pR1;

	private Tests ts1;
	
	private NurseInfo nR1;
	
	private AdminInfo aD1;
	
	private VisitDetailsDto vDD1;
	
	
	
	@BeforeEach
	void setup() {

		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		vD1=new VisitDetails();
		vD1.setPatientId(1);
		vD1.setHeight(7);
		vD1.setWeight(50);
		vD1.setBodyTemperature(97);
		vD1.setNurseEmail("abcd@gmail.com");
		vD1.setBloodGroup("B+");
		
		
		
		pR1=new Prescription();
		pR1.setPrescriptionId(1);
		pR1.setPrescriptionName("Dolo");
		pR1.setDosage("1-1-1");
	
		ts1=new Tests();
		ts1.setTestId(1);
		ts1.setTestName("Blood");
		ts1.setTestNotes("Very good");
		
		nR1=new NurseInfo();
		nR1.setFirst_Name("a");
		nR1.setLast_Name("b");
		nR1.setNurse_email("abcd@gmail.com");
		
		vDD1=new VisitDetailsDto();
		vDD1.setPatientId(1);
		vDD1.setPhysicianEmail("abcd@123");
		vDD1.setNurseEmail("efgh@gmail.com");
		
		
	}
	
	
	@Test
	void FindPatientBloodGroup() throws Exception {

		
		Optional<VisitDetails> x=Optional.of(vD1);
		when(patientHealthRecordService.findBloodGroupForPatient(anyInt())).thenReturn(x);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{patientId}/bloodgroup",1))
		.andExpect(status().isOk());
		
	}
	
	
	
	@Test
	void getPatientDetailsByappointmentid() throws Exception {

		
		Optional<VisitDetails> x=Optional.of(vD1);
		when(patientHealthRecordService.getDetailsByAppId(anyInt())).thenReturn(x);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/visitDetails/{appointmentId}",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void healthRecordsById() throws Exception {

		
		Optional<VisitDetails> x=Optional.of(vD1);
		when(patientHealthRecordService.findById(anyInt())).thenReturn(x);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/health-records/{appointmentId}",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void healthRecordsByIdAppointmentId() throws Exception {

		
		when(patientHealthRecordService.findVisistDetailsByAppointmentId(anyInt())).thenReturn(vD1);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{patientId}/health-records",1))
		.andExpect(status().isOk());
	}
	
	@Test
	void previousVisistDetailsRecords() throws Exception {

		
		when(patientHealthRecordService.getPreviousVisitDetailsByPatientId(anyInt())).thenReturn(vD1);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/Previous-visitDetails-records/{patientId}",1))
		.andExpect(status().isOk());
	}
	
	@Test
	void getPrescriptionById() throws Exception {

		List<Prescription>list=new ArrayList<>();
		list.add(pR1);
		
		when(patientHealthRecordService.findPrescriptionById(anyInt())).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/prescription/{visitId}",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void getVisitDetailsByPatientIdForHistory() throws Exception {

		
		when(patientHealthRecordService.getPreviousVisitDetailsByPatientIdforhistory(anyInt())).thenReturn(vD1);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/Previous-visitDetails-records-for-history/{patientId}",1))
		.andExpect(status().isOk());
	}
	
	
	
	
	
	@Test
	void updatePrescription() throws Exception {
		
		when(patientHealthRecordService.savePrescription(any(Prescription.class))).thenReturn(pR1);
		
		this.mockMvc.perform(post("http://localhost:9005/api/v1/patient/prescription")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pR1)))
		.andExpect(status().isOk());
			
	}
	
	
	@Test
	void updateTestByTestId() throws Exception {
		
		when(patientHealthRecordService.updateForTestByTestId(anyInt(),any(Tests.class))).thenReturn(ts1);		
		this.mockMvc.perform(put("http://localhost:9005/api/v1/updateTest/{testId}", 1,ts1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ts1)))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void deletePrescriptionById() throws Exception {
		
		when(patientHealthRecordService.deletePrescription(anyInt())).thenReturn(true);
		
		this.mockMvc.perform(delete("http://localhost:9005/api/v1/deletePrescription/{prescriptionId}", 1))
			.andExpect(status().isOk());
			
	}
	
	@Test
	void saveTest() throws Exception {
		
		when(patientHealthRecordService.saveTest(any(Tests.class))).thenReturn(ts1);
		
		this.mockMvc.perform(post("http://localhost:9005/api/v1/savetest")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ts1)))
		.andExpect(status().isOk());
			
	}
	
	
	
	@Test
	void delete1() throws Exception {
		
		
		doNothing().when(patientHealthRecordService).deleteTest(anyInt());
		
		this.mockMvc.perform(delete("http://localhost:9005/api/v1/tests/{testId}", 1))
			.andExpect(status().isOk());
			
	}
	
	
	@Test
	void nurseCount() throws Exception {

		
		when(patientHealthRecordService.countNurses()).thenReturn(1L);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/nurseCount",1))
		.andExpect(status().isOk());
	}
	
	@Test
	void adminCount() throws Exception {

		
		when(patientHealthRecordService.countAdmins()).thenReturn(1L);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/adminCount",1))
		.andExpect(status().isOk());
	}
	
//	@Test
//	void adminCount() throws Exception {
//
//		List<Admin> list = new ArrayList<>();
//		list.add(p1);
//		
//		
//		when(patientInfoService.countPatient()).thenReturn((long) list.size());
//		
//		
//		this.mockMvc.perform(get("http://localhost:9006/api/v1/patientCount"))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$", is(list.size())));
//	}
	
	
	@Test
	void findVisitDetailsByVisitId() throws Exception {

		List<VisitDetails>list=new ArrayList<>();
		when(patientHealthRecordService.findVisitDetailsByVisitId(anyInt())).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{visitId}/visit-details",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void getNurseDetails() throws Exception {

		
		when(patientHealthRecordService.getNurseDetails(any())).thenReturn( nR1);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/nursedetails/{nurse_email}","abcd@gmail.com"))
		.andExpect(status().isOk());
	}
	
	@Test
	void findVisitIdByPatientId() throws Exception {

		List<Integer>list=new ArrayList<>();
		list.add(1);
		list.add(2);
		when(patientHealthRecordService.findVisitIdByPatientId(anyInt())).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{patientId}/visitId",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void AdminList() throws Exception {

		List<AdminInfo>list=new ArrayList<>();
		list.add(aD1);
		when(patientHealthRecordService.AdminfindAll()).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/admins"))
		.andExpect(status().isOk());
	}
	
	@Test
	void NurseList() throws Exception {

		List<NurseInfo>list=new ArrayList<>();
		list.add(nR1);
		when(patientHealthRecordService.NursefindAll()).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/nurses"))
		.andExpect(status().isOk());
	}
	
	@Test
	void findTestsByVisitId() throws Exception {

		List<Tests>list=new ArrayList<>();
		list.add(ts1);
		
		when(patientHealthRecordService.findTestByVisitId(anyInt())).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{visitId}/test-records",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void insertVisitDetials() throws Exception {
		
		when(patientHealthRecordService.createVisitDetails(any(VisitDetailsDto.class))).thenReturn(vDD1);
		
		this.mockMvc.perform(post("http://localhost:9005/api/v1/patient/health-records")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vDD1)))
		.andExpect(status().isOk());
			
	}
	
	
	@Test
	void updatePatientInforDoctors() throws Exception {
		
		when(patientHealthRecordService.updateVisitDetials(anyInt(),any(VisitDetailsDto.class))).thenReturn(vDD1);		
		this.mockMvc.perform(put("http://localhost:9005/api/v1/patient/{patientId}/health-records", 1,vDD1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(vDD1)))
		.andExpect(status().isOk());
	}
	
	@Test
	void updateTest() throws Exception {
		
		when(patientHealthRecordService.updateforTest(any(Tests.class))).thenReturn(ts1);
		
		this.mockMvc.perform(post("http://localhost:9005/api/v1/patient/{visitId}/tests",ts1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ts1)))
		.andExpect(status().isOk());
			
	}
	
	
	@Test
	void findAllPrescription() throws Exception {

		List<Prescription>list=new ArrayList<>();
		list.add(pR1);
		
		when(patientHealthRecordService.findAllPriscription()).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/prescription"))
		.andExpect(status().isOk());
	}
	
	@Test
	void findPrescriptionsByVisitId() throws Exception {

		List<Prescription>list=new ArrayList<>();
		list.add(pR1);
		
		when(patientHealthRecordService.findPrescriptionByVisitId(anyInt())).thenReturn(list);
		
		
		this.mockMvc.perform(get("http://localhost:9005/api/v1/patient/{visitId}/prescription",1))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void deleteAdmin() throws Exception {
		
		
		doNothing().when(patientHealthRecordService).deleteAdmin(anyString());
		
		this.mockMvc.perform(delete("http://localhost:9005/api/v1//deleteAdmin/{adminEmail}", 1))
			.andExpect(status().isOk());
			
	}
	
	@Test
	void deleteNurse() throws Exception {
		
		
		doNothing().when(patientHealthRecordService).deleteNurse(anyString());
		
		this.mockMvc.perform(delete("http://localhost:9005/api/v1///deleteNurse/{nurseEmail}", 1))
			.andExpect(status().isOk());
			
	}
	
	
	
}