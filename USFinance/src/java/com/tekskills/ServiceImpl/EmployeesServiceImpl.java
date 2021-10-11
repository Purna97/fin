package com.tekskills.ServiceImpl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekskills.Dao.EmployeesDao;
import com.tekskills.Dto.DocumentHistoryDto;
import com.tekskills.Dto.EmployeeDto;
import com.tekskills.Dto.ManagerDto;
import com.tekskills.Entity.CandidateDetailedInfo;
import com.tekskills.Entity.DepartmentsEntity;
import com.tekskills.Entity.EmpBasicDetailsEntity;
import com.tekskills.Entity.EmpRoles;
import com.tekskills.Entity.EmpTypeMaster;
import com.tekskills.Entity.WorkAuthorization;
import com.tekskills.Service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	private static final Logger logger = Logger.getLogger(EmployeesServiceImpl.class);
	@Autowired
	EmployeesDao empDao;
	
	
	public EmpBasicDetailsEntity checkLogin(String userName, String password) {
		EmpBasicDetailsEntity basicObj = null;
		try {
			basicObj = empDao.checkLogin(userName, password);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return basicObj;
	}

	@Override
	public String checkUserName(String fullname, String middleName, String familyName) {
		String unamenoSpace = null;
		try {
			String firstLetter = String.valueOf(familyName.charAt(0));
			String uname = fullname.trim() + "." + firstLetter;
			EmpBasicDetailsEntity basicObj = empDao.checkUserName(uname);
			if (basicObj != null) {
				Integer flag = 0;
				if (basicObj.getFlag() != null) {
					flag = basicObj.getFlag() + 1;
				} else {
					flag = 1;
				}
				String uname1 = basicObj.getUserName() + flag;
				basicObj.setFlag(1);
				empDao.updateFlag(basicObj);
				String userName = uname1;
				unamenoSpace = userName.replaceAll("\\s", "");
			} else {
				unamenoSpace =uname.replaceAll("\\s", "");
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return unamenoSpace;
	}

	@Override
	public EmpBasicDetailsEntity getEmployeeDetailsByCandidateId(Integer candidate_id) {
		EmpBasicDetailsEntity basicObj = null;
		try {
			basicObj = empDao.getEmployeeDetailsByCandidateId(candidate_id);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return basicObj;
	}

	@Override
	public List<WorkAuthorization> getWorkAuthnList() {
		List<WorkAuthorization> waList = null;
		try {
			waList = empDao.getWorkAuthnList();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return waList;
	}

	@Override
	public List<EmpTypeMaster> getEmployeeTypeList() {
		List<EmpTypeMaster> emptypeList = null;
		try {
			emptypeList = empDao.getEmployeeTypeList();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return emptypeList;
	}

	@Override
	public List<DepartmentsEntity> getdepartmentList() {
		List<DepartmentsEntity> deptList = null;
		try {
			deptList = empDao.getdepartmentList();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return deptList;
	}

	@Override
	public List<EmpRoles> getroleList() {
		List<EmpRoles> roleList = null;
		try {
			roleList = empDao.getroleList();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return roleList;
	}
	@Override
	public CandidateDetailedInfo getCandidateDetailedInfoById(int candidate_id) {
		CandidateDetailedInfo detailObj = null;
		try {
			detailObj = empDao.getCandidateDetailedInfoById(candidate_id);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}
		return detailObj;
	}

	@Override
    public EmployeeDto getCanidateDetailsByCanId(Integer candidateId) {
           EmployeeDto basicObj = null;
           try {
                  basicObj = empDao.getCanidateDetailsByCanId(candidateId);
           } catch (Exception e) {
                  logger.info(e.getMessage(), e);
           }
           return basicObj;
    }
	@Override
	public String checkPassword(String userName, String bytesCurrepwd) {
		String msg = "";
		try {
			msg = empDao.checkPassword(userName, bytesCurrepwd);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return msg;
	}

	@Override
	public String changePassword(String userName, String bytesEncodepwd) {
		String msg2 = "";
		try {
			msg2 = empDao.changePassword(userName, bytesEncodepwd);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return msg2;
	}

	@Override
	public String getempTypeByCanId(String canid) {
		String msg2 = "";
		try {
			int id=0;
			if(!canid.isEmpty()) {
				id=Integer.parseInt(canid);
			}
			msg2 = empDao.getempTypeByCanId(id);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return msg2;
	}

	

	

	
	@Override
	public List<DocumentHistoryDto> getuserNameBycandId(String candId) {
		 List<DocumentHistoryDto> users=null;
		 System.out.println("getuserNameBycandId IMPL----");
	try {
		Integer candidate_Id = 0;
		if (candId!="") {
			candidate_Id = Integer.parseInt(candId);
		}
		users=empDao.getuserNameBycandId(candidate_Id);
	} catch (Exception e) {
		logger.info(e.getMessage(),e);
	}
	return users;
}

	@Override
	public List<ManagerDto> getHRList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerDto> getrepMagrList() {
		// TODO Auto-generated method stub
		return null;
	}

	


	

}
