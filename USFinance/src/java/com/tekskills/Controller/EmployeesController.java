package com.tekskills.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekskills.Dto.MyDashboardDto;
import com.tekskills.Entity.CandidateDetailedInfo;
import com.tekskills.Entity.EmpBasicDetailsEntity;
import com.tekskills.Service.EmployeesService;

@Controller
@PropertySource("/WEB-INF/mail.properties")
public class EmployeesController {
	private static final Logger logger = Logger.getLogger(EmployeesController.class);
	@Autowired
	private EmployeesService empService;	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = { "/loginpage" }, method = RequestMethod.GET)
	public String Loginform(HttpServletRequest req) {
		return "login";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		try {
			String userName = (String) req.getSession().getAttribute("username");
			if (userName != null) {
				session.invalidate();
				return "redirect:/loginpage";
			} else {
				return "redirect:/loginpage";
			}
		} catch (Exception e) {
			logger.error("Error in logout EmployeesController source" + e);
		}
		return "redirect:/loginpage";
	}

	@RequestMapping("/ChangePassword")
	public String changePwd(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} else {
				return "ChangePwd";
			}
		} catch (Exception e) {
			logger.error("Error in changePwd " + e);
		}
		return null;
	}

	@RequestMapping(value = { "/ChangeLoginPassword" }, method = RequestMethod.POST)
	@ResponseBody
	public String changeLoginPassword(Map<String, Object> map, Model model, HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String message = null;
		try {
			String userName = (String) req.getSession().getAttribute("username");
			if (userName != null) {
				String currentPassword = req.getParameter("currentPwd");
				String confirmPassword = req.getParameter("confirmPassword");
				String checkPassword = empService.checkPassword(userName, currentPassword);
				if (checkPassword == null) {
					message = "Current password is not Correct";
				} else {
					String msg = empService.changePassword(userName, confirmPassword);
					if (msg.equalsIgnoreCase("success")) {
						message = "password changed Successfully";
					} else {
						message = "password not changed";
					}
				}
			} else {
				message = null;
			}
		} catch (Exception e) {
			logger.error("Error in ChangePassword" + e);
		}
		return message;
	}

	@RequestMapping(value = { "/validateLogin" }, method = RequestMethod.POST)
	public String validateLogin(Model model, HttpServletRequest request, HttpServletResponse response) {
		String UserName = request.getParameter("username").trim();
		String Password = request.getParameter("password").trim();
		
		HttpSession session = request.getSession(true);
		
				EmpBasicDetailsEntity basicObj = empService.checkLogin(UserName, Password);
				if (basicObj != null) {
					MyDashboardDto myDashboard = null;
					CandidateDetailedInfo detailObj = empService
							.getCandidateDetailedInfoById(basicObj.getCandidate_id());
					//myDashboard = empService.getmyDashboardDocList();
					//List<TimesheetDto> timesheetList = null;
					if (detailObj.getRole_id() != 0) {
						if(detailObj.getRole_id() == 1 && detailObj.getEmp_type().equalsIgnoreCase("W2")) {
							String status = "Submitted";
							String month = "";
							String customertype = "";
							LocalDate currentDate = LocalDate.now();
							Integer curyear = currentDate.getYear();
							String year = String.valueOf(curyear);
							//timesheetList = tsServie.getPendingViewTimesheet(year, status, detailObj.getRole_id(),
									//basicObj.getCandidate_id(), detailObj.getEmp_type(), month, customertype);
						}else if (detailObj.getEmp_type().equalsIgnoreCase("W2 Admin") || detailObj.getEmp_type().equalsIgnoreCase("Admin")) {
							if(detailObj.getRole_id() == 1 || detailObj.getRole_id() == 2) {
								System.out.println(detailObj.getRole_id()+"detailObj.getRole_id()***"+detailObj.getEmp_type());
								String status = "Submitted";
								String month = "";
								String customertype = "";
								LocalDate currentDate = LocalDate.now();
								Integer curyear = currentDate.getYear();
								String year = String.valueOf(curyear);
								//timesheetList = tsServie.getPendingViewTimesheet(year, status, detailObj.getRole_id(),
										//basicObj.getCandidate_id(), detailObj.getEmp_type(), month, customertype);
							}else {
							String status = "Submitted";
							String month = "";
							String customertype = "";
							LocalDate currentDate = LocalDate.now();
							Integer curyear = currentDate.getYear();
							String year = String.valueOf(curyear);
							Integer candidateId = 0;
							//timesheetList = tsServie.getPendingViewTimesheet(year, status, detailObj.getRole_id(),
									//candidateId, detailObj.getEmp_type(), month, customertype);
							}
						}
					}
					//session.setAttribute("timesheetList", timesheetList);

					session.setAttribute("username", basicObj.getUserName());
					session.setAttribute("lgfullname", basicObj.getFullName());
					session.setAttribute("emptype", detailObj.getEmp_type());
					session.setAttribute("role", detailObj.getRole_id());
					session.setAttribute("dept", detailObj.getDepartment_id());
					session.setAttribute("desg", detailObj.getDesignation());
					session.setAttribute("loginUserid", basicObj.getCandidate_id());
					session.setAttribute("empno", basicObj.getEmp_number());
					session.setAttribute("lgworkAuth", detailObj.getWork_authorization());
					session.setAttribute("payrollType", detailObj.getPayroll_type());
					session.setAttribute("validateby", detailObj.getValidateby_flag());
					session.setAttribute("candidateid", basicObj.getCandidate_id());
					session.setAttribute("joiningdate", detailObj.getJoining_date());
					session.setAttribute("myDashboard", myDashboard);
					session.setAttribute("emailid", basicObj.getEmail());
					session.setAttribute("personaldetails", null);
					session.setAttribute("lgemailid", null);
					session.setAttribute("invalid", null);

					session.setAttribute("emailid", basicObj.getEmail());
					session.setAttribute("personaldetails", null);
				
					if (detailObj.getRole_id() > 0) {
						return "Dashboard";
					} else {
						session.setAttribute("invalid", "Please contact to employer");
						return "redirect:/loginpage";
					}
				} else {
					session.setAttribute("invalid", "Invalid User Name or Password");
					return "redirect:/loginpage";
				}
	}

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String Dashboard(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String) request.getSession().getAttribute("username");
		Integer role = (Integer) request.getSession().getAttribute("role");
		Integer lgcandidateid = (Integer) request.getSession().getAttribute("candidateid");
		String lgemptype = (String) request.getSession().getAttribute("emptype");
		try {
			if (userName == null) {
				return "redirect:/loginpage";
			} else {
				
			}
		} catch (Exception e) {
			logger.error("Error in dashboard " + e);
		}
		return "Dashboard";
	}
	@RequestMapping(value = { "/myDashboard" }, method = RequestMethod.GET)
	public String myDashboard(HttpServletRequest request, HttpServletResponse response) {
		String userName = (String) request.getSession().getAttribute("username");
		try {
			if (userName == null) {
				return "redirect:/loginpage";
			} else {
				MyDashboardDto myDashboard = null;
				HttpSession session = request.getSession(true);
				//myDashboard = empService.getmyDashboardDocList();
				String mdashboard = "mdashboard";
				session.setAttribute("mdashboard", mdashboard);
				session.setAttribute("candidates", null);
				session.setAttribute("templates", null);
				session.setAttribute("stsreports", null);
				session.setAttribute("timesheet", null);
				session.setAttribute("bgc", null);
				session.setAttribute("candidates", null);
				session.setAttribute("myDashboard", myDashboard);
				session.setAttribute("immigration", null);
				session.setAttribute("myimmidocs", null);
				return "myDashboard";
			}
		} catch (Exception e) {
			logger.error("Error in myDashboard " + e);
		}
		return null;

	}

}
