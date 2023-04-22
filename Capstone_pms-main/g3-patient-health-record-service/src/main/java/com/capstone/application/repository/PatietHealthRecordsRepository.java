package com.capstone.application.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.application.model.VisitDetails;

public interface PatietHealthRecordsRepository extends JpaRepository<VisitDetails,Integer> {
	
	@Query(value="select visit_id from visit_details where patient_id=?1 order by visit_id desc limit 12", nativeQuery=true)
	List<Integer> findVisitIdByPatientId(int patientId);

	
	//aakash
	@Query(value = "Select * from visit_details  where patient_id=:id order by visit_id desc limit 1", nativeQuery =true)
	public VisitDetails  getPreviousVisitIdDetailsById(@Param(value="id") Integer id)	;

	@Query(value="Select * from visit_details where appointment_id=:id", nativeQuery =true)
	 public VisitDetails findVisitDetailsById(@Param(value = "id") Integer id);
	
	//Eshwari
	@Query(value="select * from visit_details where patient_id =?1 and blood_group is not null limit 1", nativeQuery=true)

	public Optional< VisitDetails> getBloodGroup(int patientId);



	@Query(value ="Select * from visit_details where appointment_id =?1 limit 1" , nativeQuery = true)

	Optional<VisitDetails> getVisitDetailsBtAppId(int appointmentId);
	
	//mrunal
	@Query(value="select * from visit_details where visit_id=?1",nativeQuery=true)
	public List<VisitDetails> findVisitDetailsByVisitId(int visitId);
}
