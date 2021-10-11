package com.tekskills.Dao;

import java.util.List;

import com.tekskills.Dto.BasicDetailsDto;

import com.tekskills.Dto.DocumentHistoryDto;
import com.tekskills.Dto.EmployeeDto;
import com.tekskills.Dto.ManagerDto;
import com.tekskills.Dto.MyDashboardDto;

import com.tekskills.Entity.CandidateAddress;
import com.tekskills.Entity.CandidateDetailedInfo;

import com.tekskills.Entity.CompanyAdressEntity;
import com.tekskills.Entity.CustomerMasterEntity;
import com.tekskills.Entity.DepartmentsEntity;
import com.tekskills.Entity.EmpBasicDetailsEntity;

import com.tekskills.Entity.EmpRoles;
import com.tekskills.Entity.EmpTypeMaster;
import com.tekskills.Entity.PageStatusEntity;
import com.tekskills.Entity.VendorMasterEntity;
import com.tekskills.Entity.WorkAuthorization;

public interface EmployeesDao {

	EmpBasicDetailsEntity checkLogin(String userName, String password);

	Integer saveBasicDetails(EmpBasicDetailsEntity basicObj);

	Integer getEmployeeNo();

	boolean checkEmpExists(int emp_number);

	EmpBasicDetailsEntity checkUserName(String uname);

	void updateFlag(EmpBasicDetailsEntity basicObj);

	EmpBasicDetailsEntity getEmployeeDetailsByCandidateId(Integer userid);

	List<WorkAuthorization> getWorkAuthnList();

	List<EmpTypeMaster> getEmployeeTypeList();

	List<DepartmentsEntity> getdepartmentList();

	List<EmpRoles> getroleList();

	List<ManagerDto> getHRList();

	List<ManagerDto> getrepMagrList();

	EmpBasicDetailsEntity SSNExistOrNot(String ssn_emp);


	List<ManagerDto> getContractTeamList();

	Integer saveVendorMaster(VendorMasterEntity venObj);

	String checkSelectedFileName(String filename);


	Integer saveCustomerMaster(CustomerMasterEntity custObj);


	CustomerMasterEntity getCustomerDetailsById(Integer customerId);


	VendorMasterEntity getVendorDetailsById(Integer vendorId);


	
	PageStatusEntity getRedirectPageStatus(Integer userid);

	void savePageStatus(PageStatusEntity pagestsObj);

	

	List<ManagerDto> getEmpInfoByEmpType(String sEmp_type);


	BasicDetailsDto viewEmployeeInfo(Integer candidateId);

	
	BasicDetailsDto viewConsultantInfo(Integer candidateId);

	
	CandidateDetailedInfo getCandidateDetailedInfoById(int candidate_id);

	void saveCandidateAddressDetails(CandidateAddress candAddressObj);

	void saveCandidateDetailedInfo(CandidateDetailedInfo candDetailObj);

	CandidateAddress getCandidateAddressByCandidateId(Integer candidateId);

	void updateCandidateDetailedInfo(CandidateDetailedInfo candidateInfoDBObj);

	void updateCandidateAddressInfo(CandidateAddress candidateAddrDBObj);

	List<WorkAuthorization> getWorkAuthnListForContractors();

	void saveCompanyAddressDetails(CompanyAdressEntity custAddressObj);

	EmployeeDto getCanidateDetailsByCanId(Integer candidateId);

	CompanyAdressEntity getCompanyAdressDetailsById(Integer customerId, String companyType);

	void updateCompanyAddressInfo(CompanyAdressEntity clientAddressObjDB);




	Integer getJoinHistoryId(Integer candidateId);

	String checkPassword(String userName, String bytesCurrepwd);

	String changePassword(String userName, String bytesEncodepwd);


	EmpBasicDetailsEntity getEmployeeStatusDetailsByCandidateId(Integer candidate_Id);

	String getempTypeByCanId(int id);

	BasicDetailsDto checkPhoneNo(String phoneno);

	String checkEmail(String email);




	List<DocumentHistoryDto> getCustomerDocumentHistiryByCustomerId(Integer cust_Id);

	void updateCustomerMaster(CustomerMasterEntity custDBObj);

	void updateVendorMaster(VendorMasterEntity vendDBObj);

	List<DocumentHistoryDto> getVendorDocumentHistiryById(Integer venId, String fileType, String inputVal);

	
	List<DocumentHistoryDto> getClientCustomerDocumentHistiryById(Integer cust_Id, Integer candidate_Id);

	List<DocumentHistoryDto> getClientVendorDocumentHistiryById(Integer ven_Id, Integer candidate_Id, String fileType);

	List<DocumentHistoryDto> getConsultantDocumentHistiryById(Integer candidate_Id, String fileType, String inputVal);

	Integer getVendorIdByPayrateId(Integer candidate_Id, Integer payrateid);


	List<String> geti9SupportDocumentsListByType(String i9sdType);

	List<String> geti9SupportDocumentsListByCandidateIdType(Integer candidate_Id, String i9sdType);

	List<MyDashboardDto> getmyDashboardDocList();



	String checkSelectedFileInput(String docInput);

	BasicDetailsDto viewEmployeeNotWithAddress(Integer candidateId);

	

	List<DocumentHistoryDto> getDocTypesByCandId(Integer candidate_Id);


	List<Integer> getchecklistIds(Integer candidate_Id,  Integer subdocId, Integer docId, Integer initiateid);

	List<DocumentHistoryDto> getFilesBychklistid(List<Integer> list);

	List<DocumentHistoryDto> getsubDocTypesBydocId(Integer candidate_Id, Integer profileid);


	List<DocumentHistoryDto> getuserNameBycandId(Integer candidate_Id);

	Integer getactiveclientListByCandidate(Integer candidate_Id, String clienttype);

	List<String> getFiles(Integer candidate_Id, Integer docId, Integer subdocId, Integer initiateid, List<Integer> list);


	Integer getCurrinitiateidBycandId(Integer candidate_Id);

	List<DocumentHistoryDto> getDocumentHistiryById(Integer doc_Id);

	

}
