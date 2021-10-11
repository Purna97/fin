package com.tekskills.DaoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.tekskills.Dao.EmployeesDao;
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

@Repository
public class EmployeesDaoImpl implements EmployeesDao {
	private static final Logger logger = Logger.getLogger(EmployeesDaoImpl.class);
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessfact;

	@Override
	public EmpBasicDetailsEntity checkLogin(String userName, String password) {
		EmpBasicDetailsEntity basicObj = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String bytesEncoded = Base64.encode(password.getBytes());
			String sql = "from EmpBasicDetailsEntity  where userName='" + userName + "' and pwd='" + bytesEncoded
					+ "'  and status=1";
			basicObj = (EmpBasicDetailsEntity) session.createQuery(sql).uniqueResult();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return basicObj;
	}

	@Override
	public Integer saveBasicDetails(EmpBasicDetailsEntity basicObj) {
		Session session = null;
		Integer candidateId = null;
		try {
			session = sessfact.openSession();
			Transaction tx = session.beginTransaction();
			candidateId = (Integer) session.save(basicObj);
			tx.commit();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return candidateId;
	}

	@Override
	public Integer getEmployeeNo() {
		Session session = null;
		Integer empNo=null;
		try {
			session = sessfact.openSession();
			//String sql = "SELECT TOP 1 emp_number FROM Tbl_EmpBasicDetails  where emp_type in ('W2 Admin','W2') ORDER BY candidate_id DESC";
			String sql = "select TOP 1 a.emp_number from Tbl_EmpBasicDetails a,Tbl_CandidateDetailedInfo c where a.candidate_id=c.candidate_id and c.emp_type in ('W2 Admin','W2','Admin') and c.status='Active' and a.status=1 ORDER BY a.candidate_id DESC";
			empNo = (Integer) session.createSQLQuery(sql).uniqueResult();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return empNo;
	}

	@Override
	public boolean checkEmpExists(int emp_number) {
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "select emp_number from Tbl_EmpBasicDetails where emp_number=" + emp_number + "";
			Integer empNo = (Integer) session.createSQLQuery(sql).uniqueResult();
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public EmpBasicDetailsEntity checkUserName(String uname) {
		EmpBasicDetailsEntity empObj = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			// String sql = "select * from Tbl_EmpBasicDetails where username='"+uname+"'";
			// empObj = (EmpBasicDetailsEntity) session.createSQLQuery(sql).uniqueResult();
			String sql = "from EmpBasicDetailsEntity  where userName='" + uname + "'";
			empObj = (EmpBasicDetailsEntity) session.createQuery(sql).uniqueResult();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return empObj;
	}
	
	

	@Override
	public EmpBasicDetailsEntity getEmployeeDetailsByCandidateId(Integer candidate_id) {
		EmpBasicDetailsEntity basicObj = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from EmpBasicDetailsEntity  where candidate_id=" + candidate_id + " and status=1";
			basicObj = (EmpBasicDetailsEntity) session.createQuery(sql).uniqueResult();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return basicObj;
	}

	@Override
	public List<WorkAuthorization> getWorkAuthnList() {
		List<WorkAuthorization> waList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from WorkAuthorization";
			waList = (List<WorkAuthorization>) session.createQuery(sql).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return waList;
	}

	@Override
	public List<EmpTypeMaster> getEmployeeTypeList() {
		List<EmpTypeMaster> emptypeList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from EmpTypeMaster";
			emptypeList = (List<EmpTypeMaster>) session.createQuery(sql).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return emptypeList;
	}

	@Override
	public List<DepartmentsEntity> getdepartmentList() {
		List<DepartmentsEntity> deptList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from DepartmentsEntity";
			deptList = (List<DepartmentsEntity>) session.createQuery(sql).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return deptList;
	}

	@Override
	public List<EmpRoles> getroleList() {
		List<EmpRoles> roleList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from EmpRoles";
			roleList = (List<EmpRoles>) session.createQuery(sql).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return roleList;
	}

	@Override
	public List<ManagerDto> getHRList() {
		List<ManagerDto> hrList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			//String sql = "select a.full_name as hrName from Tbl_EmpBasicDetails a,Tbl_CandidateDetailedInfo c where a.candidate_id=c.candidate_id and c.emp_type='W2 Admin' and c.department_id=2 and c.status='Active' and a.status=1";
			String sql = "select a.full_name as hrName,a.email_id as emailId from Tbl_EmpBasicDetails a,Tbl_CandidateDetailedInfo c where a.candidate_id=c.candidate_id and c.emp_type='W2 Admin' and c.department_id=2 and c.status='Active' and a.status=1";
			hrList = session.createSQLQuery(sql).setResultTransformer(new AliasToBeanResultTransformer(ManagerDto.class)).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return hrList;
	}

	@Override
	public List<ManagerDto> getrepMagrList() {
		List<ManagerDto> repMagrList = null;
		Session session = null;
		try {
			session = sessfact.openSession();
		//	String sql = "from  EmpBasicDetailsEntity where employee_role not in (1) and status=1";
			//String sql = "select c.reporting_head as mgrEmpNo,a.full_name as mgrEmpName from Tbl_EmpBasicDetails a,Tbl_CandidateDetailedInfo c where a.candidate_id=c.candidate_id and c.role_id not in (1) and c.status='Active' and a.status=1";
			String sql = "select a.emp_number as mgrEmpNo,a.full_name as mgrEmpName from Tbl_EmpBasicDetails a,Tbl_CandidateDetailedInfo c where a.candidate_id=c.candidate_id and c.role_id not in (1) and c.status='Active' and a.status=1 and a.emp_number<>0";
			repMagrList = session.createSQLQuery(sql).setResultTransformer(new AliasToBeanResultTransformer(ManagerDto.class)).list();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return repMagrList;
	}

	@Override
	public EmpBasicDetailsEntity SSNExistOrNot(String ssn_emp) {
		EmpBasicDetailsEntity ssn = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String bytesEncoded = Base64.encode(ssn_emp.getBytes());
			System.out.println(bytesEncoded);
			String sql = "from EmpBasicDetailsEntity  where ssn_no='" + bytesEncoded + "'";
			ssn = (EmpBasicDetailsEntity) session.createQuery(sql).uniqueResult();

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return ssn;
	}

	
	@Override
	public BasicDetailsDto viewEmployeeInfo(Integer candidateId) {
		BasicDetailsDto basicObj = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "Exec getBaiscDetails @candidateId=" + candidateId + "";
			basicObj = (BasicDetailsDto) session.createSQLQuery(sql)
					.setResultTransformer(new AliasToBeanResultTransformer(BasicDetailsDto.class)).uniqueResult();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return basicObj;
	}

	@Override
	public void updateFlag(EmpBasicDetailsEntity basicObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManagerDto> getContractTeamList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveVendorMaster(VendorMasterEntity venObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkSelectedFileName(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveCustomerMaster(CustomerMasterEntity custObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerMasterEntity getCustomerDetailsById(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VendorMasterEntity getVendorDetailsById(Integer vendorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageStatusEntity getRedirectPageStatus(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePageStatus(PageStatusEntity pagestsObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManagerDto> getEmpInfoByEmpType(String sEmp_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicDetailsDto viewConsultantInfo(Integer candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidateDetailedInfo getCandidateDetailedInfoById(int candidate_id) {
		CandidateDetailedInfo detailObj = null;
		Session session = null;
		try {
			session = sessfact.openSession();
			String sql = "from CandidateDetailedInfo  where candidate_id="+candidate_id+" and status='Active'";
			detailObj = (CandidateDetailedInfo) session.createQuery(sql).uniqueResult();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			session.close();
		}
		return detailObj;
	}

	@Override
	public void saveCandidateAddressDetails(CandidateAddress candAddressObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCandidateDetailedInfo(CandidateDetailedInfo candDetailObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CandidateAddress getCandidateAddressByCandidateId(Integer candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCandidateDetailedInfo(CandidateDetailedInfo candidateInfoDBObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCandidateAddressInfo(CandidateAddress candidateAddrDBObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WorkAuthorization> getWorkAuthnListForContractors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCompanyAddressDetails(CompanyAdressEntity custAddressObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeDto getCanidateDetailsByCanId(Integer candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyAdressEntity getCompanyAdressDetailsById(Integer customerId, String companyType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompanyAddressInfo(CompanyAdressEntity clientAddressObjDB) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getJoinHistoryId(Integer candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkPassword(String userName, String bytesCurrepwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(String userName, String bytesEncodepwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpBasicDetailsEntity getEmployeeStatusDetailsByCandidateId(Integer candidate_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getempTypeByCanId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicDetailsDto checkPhoneNo(String phoneno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getCustomerDocumentHistiryByCustomerId(Integer cust_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomerMaster(CustomerMasterEntity custDBObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVendorMaster(VendorMasterEntity vendDBObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DocumentHistoryDto> getVendorDocumentHistiryById(Integer venId, String fileType, String inputVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getClientCustomerDocumentHistiryById(Integer cust_Id, Integer candidate_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getClientVendorDocumentHistiryById(Integer ven_Id, Integer candidate_Id,
			String fileType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getConsultantDocumentHistiryById(Integer candidate_Id, String fileType,
			String inputVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getVendorIdByPayrateId(Integer candidate_Id, Integer payrateid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> geti9SupportDocumentsListByType(String i9sdType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> geti9SupportDocumentsListByCandidateIdType(Integer candidate_Id, String i9sdType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyDashboardDto> getmyDashboardDocList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkSelectedFileInput(String docInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicDetailsDto viewEmployeeNotWithAddress(Integer candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getDocTypesByCandId(Integer candidate_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getchecklistIds(Integer candidate_Id, Integer subdocId, Integer docId, Integer initiateid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getFilesBychklistid(List<Integer> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getsubDocTypesBydocId(Integer candidate_Id, Integer profileid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getuserNameBycandId(Integer candidate_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getactiveclientListByCandidate(Integer candidate_Id, String clienttype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getFiles(Integer candidate_Id, Integer docId, Integer subdocId, Integer initiateid,
			List<Integer> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCurrinitiateidBycandId(Integer candidate_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentHistoryDto> getDocumentHistiryById(Integer doc_Id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
