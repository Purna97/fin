package com.tekskills.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tekskills.Dto.BasicDetailsDto;
import com.tekskills.Dto.DocumentHistoryDto;
import com.tekskills.Dto.EmployeeDto;
import com.tekskills.Dto.ManagerDto;
import com.tekskills.Dto.MyDashboardDto;
import com.tekskills.Entity.CandidateDetailedInfo;
import com.tekskills.Entity.DepartmentsEntity;
import com.tekskills.Entity.EmpBasicDetailsEntity;
import com.tekskills.Entity.EmpRoles;
import com.tekskills.Entity.EmpTypeMaster;
import com.tekskills.Entity.PageStatusEntity;
import com.tekskills.Entity.WorkAuthorization;

public interface EmployeesService {

	EmpBasicDetailsEntity checkLogin(String userName, String password);

	String checkUserName(String fullname, String middleName, String familyName);

	EmpBasicDetailsEntity getEmployeeDetailsByCandidateId(Integer userid);

	List<WorkAuthorization> getWorkAuthnList();

	List<EmpTypeMaster> getEmployeeTypeList();

	List<DepartmentsEntity> getdepartmentList();

	List<EmpRoles> getroleList();

	List<ManagerDto> getHRList();

	List<ManagerDto> getrepMagrList();


	CandidateDetailedInfo getCandidateDetailedInfoById(int candidate_id);

	EmployeeDto getCanidateDetailsByCanId(Integer candidateId);

	String checkPassword(String userName, String currentPassword);

	String changePassword(String userName, String confirmPassword);

	String getempTypeByCanId(String canid);
	List<DocumentHistoryDto> getuserNameBycandId(String candId);



	

	
}
