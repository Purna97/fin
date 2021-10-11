package com.tekskills.Controller;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.tekskills.Util.OAuth2PlatformClientFactory;

@Controller
@PropertySource("/WEB-INF/mail.properties")
public class QBRestController { 
	private static final Logger logger = Logger.getLogger(QBRestController.class);

	@Autowired
	private Environment env;

	@Autowired
	OAuth2PlatformClientFactory factory;

	@RequestMapping(value = { "/downloadinvoices" }, method = RequestMethod.GET)
	public void downloadinvoices(HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("window.location.href='login.jsp'");
				out.println("</script>");
				out.close();
			} else {
				
				String filename = request.getParameter("filename");
				String filepath = null;
				String workingDir = env.getProperty("documents");
		
				if (!filename.isEmpty()) {
					String path = workingDir + File.separator + "Invoices";
					filepath = path + File.separator + filename;
				}
		
				System.out.println(filepath);
				File ff = new File(filepath);
				if (ff.exists() == true) {
					FileInputStream fis = new FileInputStream(ff);
					byte[] inputBytes = new byte[(int) ff.length()];
					fis.read(inputBytes);
					response.setContentType("application/octet-stream;");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename);
					OutputStream os = response.getOutputStream(); // file output stream
					os.write(inputBytes);
					os.close();
				} else {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No file available');");
					out.println("window.history.back();");
					out.println("</script>");
					out.close();
				}
			}
		} catch (Exception e) {
			logger.error("Error while downloading invoice" + e);
		}
	}
	
	
	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String products(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in addemployee page" + e);
		}
		return "products";
	}
	@RequestMapping(value = { "/getproducts" }, method = RequestMethod.GET,consumes="application/text")
	@ResponseBody
	public String getproducts(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		    System.out.println("access_token----"+access_token);
		try {
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/query?query=select%20*%20from%20Item&minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/text");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		        System.out.println("ALL employees jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getallemployees " + e);
		}
		return jsonString;
	}
	
	@RequestMapping(value = { "/addemployee" }, method = RequestMethod.GET)
	public String addemployee(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in addemployee page" + e);
		}
		return "addemp";
	}
	@RequestMapping(value = { "/getallemployees" }, method = RequestMethod.GET,consumes="application/text")
	@ResponseBody
	public String getallemployees(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		    System.out.println("access_token----"+access_token);
		try {
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/query?query=select%20*%20from%20Employee&minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/text");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		        System.out.println("ALL employees jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getallemployees " + e);
		}
		return jsonString;
	}
	
	
	
	@RequestMapping(value = { "/addcustomer" }, method = RequestMethod.GET)
	public String addcustomer(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in addcustomer page" + e);
		}
		return "addcust";
	}
	@RequestMapping(value = { "/getallcustomers" }, method = RequestMethod.GET,consumes="application/text")
	@ResponseBody
	public String getallcustomers(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		    System.out.println("access_token----"+access_token);
		try {
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/query?query=select%20*%20from%20Customer&minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/text");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		        System.out.println("ALL employees jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getallcustomers " + e);
		}
		return jsonString;
	}
	
	@RequestMapping(value = { "/checktokens" }, method = RequestMethod.GET)
	@ResponseBody
	public String checktokens(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String msg=null;
		try {
			  String access_token = (String) request.getSession().getAttribute("access_token");
			  URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/companyinfo/4620816365184153280?minorversion=62");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+ access_token);
		        int code = conn.getResponseCode();
			  if(access_token==null || code == 401) {
				 msg="Token expired" ;
			  }else {
				 msg="Token available" ;
			  }
		} catch (Exception e) {
			logger.error("Error in tokens" + e);
		}
		return msg;
	}

	@RequestMapping(value = { "/invoicepage" }, method = RequestMethod.GET)
	public String invoicepage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in expense page" + e);
		}
		return "invoicegen";
	}

	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String reports(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in reports page" + e);
		}
		return "reports";
	}
	
	@RequestMapping(value = { "/expensepage" }, method = RequestMethod.GET)
	public String invoicegeneration(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		try {
			String userName = (String) request.getSession().getAttribute("username");
			if (userName == null) {
				return "redirect:/loginpage";
			} 
		} catch (Exception e) {
			logger.error("Error in expense page" + e);
		}
		return "expensegen";
	}

	@RequestMapping(value = { "/generateinvoice" }, method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public String generateinvoice(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		try {
			String invoice = request.getParameter("invoiceid");
			  URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/invoice/"+invoice+"?minorversion=62");
			  String access_token = (String) request.getSession().getAttribute("access_token");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+ access_token);
		        conn.setRequestProperty("Content-Type","application/json");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
			      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in invoice data generation " + e);
		}
		return jsonString;
	}
	
	
	@RequestMapping(value = { "/getpurcharsebyId" }, method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public String getpurcharsebyId(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		try {
			String purchaseid = request.getParameter("purchaseid");
			  URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/purchase/"+purchaseid+"?minorversion=62");
			 System.out.println("url="+url);
			  String access_token = (String) request.getSession().getAttribute("access_token");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+ access_token);
		        conn.setRequestProperty("Content-Type","application/json");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        System.out.println("purchase jsonString--"+jsonString);
			      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in purchase by ID" + e);
		}
		return jsonString;
	}
	
	
	@RequestMapping(value = { "/getallpurchases" }, method = RequestMethod.GET,consumes="application/text")
	@ResponseBody
	public String getallpurchases(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		    System.out.println("access_token----"+access_token);
		try {
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/query?query=select%20*%20from%20purchase&minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/text");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		        System.out.println("ALL purchase jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getallInvoiceIds " + e);
		}
		return jsonString;
	}
	
	
	
	
	@RequestMapping(value = { "/getallInvoiceIds" }, method = RequestMethod.GET,consumes="application/text")
	@ResponseBody
	public String getallInvoiceIds(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		    System.out.println("access_token----"+access_token);
		try {
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/query?query=select%20*%20from%20Invoice&minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/text");
		        conn.setRequestMethod("GET");

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
			      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getallInvoiceIds " + e);
		}
		return jsonString;
	}
	
	@RequestMapping(value = { "/getinvoicepdf" }, method = RequestMethod.GET, produces = "application/pdf")
	@ResponseBody
	public String getinvoicepdf(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String msg=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		try {
			String invoiceid = request.getParameter("invoiceid");
			System.out.println("invoiceid-"+invoiceid);
			   URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/invoice/"+invoiceid+"/pdf?minorversion=62");
			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/pdf");
		        conn.setRequestMethod("GET");
		     
		        String filename=null;
		        String finalpath=null;
		        filename="InvoiceId_"+invoiceid+".pdf";
				 String directorypath = env.getProperty("documents");
				 String directorypath2 =null;
				 directorypath2 = directorypath + File.separator + "Invoices";
				if (!new File(directorypath).exists()) {
					new File(directorypath).mkdir();
					if (!new File(directorypath2).exists()) {
						new File(directorypath2).mkdir();
					}
				}else {
					if (!new File(directorypath2).exists()) {
						new File(directorypath2).mkdir();
					}
				}
				finalpath=directorypath2 + File.separator + filename;
				 InputStream is = conn.getInputStream(); 
			        OutputStream os = new FileOutputStream(finalpath); 
			        byte[] bytes = IOUtils.toByteArray(is);
		
		        os.write(bytes);
		        os.flush();
		        os.close();
		 
		msg="Invoice downloaded";
			      
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in getinvoicepdf " + e);
		}
		return msg;
	}
	
	@RequestMapping(value = { "/createproduct" }, method = RequestMethod.POST)
	@ResponseBody
	public String createproduct(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		try {
			    String productname = request.getParameter("productName");
			    String hiredate = request.getParameter("hiredate");
			    String client = request.getParameter("client");
			    String unitprice = request.getParameter("unitprice");
			    String description = request.getParameter("description");
			    
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = format.parse (hiredate);  
                SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
			    String newdate=mdyFormat.format(date);
			    String hireddate=newdate.replace("-","");
		
			    String name=productname+"_"+client+hireddate;
	
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/item?minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
		        conn.setDoOutput(true);
		        conn.setRequestMethod("POST");
		   
		        String json = "{\r\n" + 
		        		"  \"Name\": \""+name+"\",\r\n" + 
		        		"  \"IncomeAccountRef\": {\r\n" + 
		        		"    \"value\": \"79\",\r\n" + 
		        		"    \"name\": \"Sales of Product Income\"\r\n" + 
		        		"  },\r\n" + 
		        		" \r\n" + 
		        		"  \"ExpenseAccountRef\": {\r\n" + 
		        		"    \"value\": \"80\",\r\n" + 
		        		"    \"name\": \"Cost of Goods Sold\"\r\n" + 
		        		"  },\r\n" + 
		        		"  \"AssetAccountRef\": {\r\n" + 
		        		"    \"value\": \"81\",\r\n" + 
		        		"    \"name\": \"Inventory Asset\"\r\n" + 
		        		"  },\r\n" + 
		        		"  \"UnitPrice\": "+unitprice+", \r\n" + 
		        		"  \"Type\": \"Service\",\r\n" + 
		        		"  \"InvStartDate\": \"2015-01-01\",\r\n" + 
		        		"  \"Description\": \""+description+"\"\r\n" + 
		        		"}";
		        System.out.println("json FORMAT+++++++++++-"+json);
		        
		        OutputStream os = conn.getOutputStream();
		           os.write(json.getBytes("UTF-8"));
		           os.close();

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		       System.out.println("create purchase jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in creating purchase " + e);
		}
		return jsonString;
	}

	@RequestMapping(value = { "/createpurchase" }, method = RequestMethod.POST)
	@ResponseBody
	public String createpurchase(Model model, HttpServletRequest request) {
		  HttpURLConnection connection = null;
		  String jsonString=null;
		  String access_token = (String) request.getSession().getAttribute("access_token");
		try {
			    String vendorid = request.getParameter("vendorid");
			    String purchaseamt = request.getParameter("purchaseamt");
			    float amt=Float.parseFloat(purchaseamt);
			    String paytype = request.getParameter("paytype");
			    String paymode = request.getParameter("paymode");
			
			    URL url = new URL("https://sandbox-quickbooks.api.intuit.com/v3/company/4620816365184153280/purchase?minorversion=62");

			    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestProperty("Authorization","Bearer "+access_token);
		        conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
		        conn.setDoOutput(true);
		        conn.setRequestMethod("POST");
		   
		        String json = "{\r\n" + 
		        		"  \"PaymentType\": \""+paytype+"\", \r\n" + 
		        		"   \"EntityRef\": {\r\n" + 
		        		"   \"value\": \""+vendorid+"\",\r\n" +    
		        		"   \"name\": \"tekskills\",\r\n" + 
		        		"   \"type\": \"Vendor\"\r\n" + 
		        		"  },\r\n" + 
		        		"  \"AccountRef\": {\r\n" + 
		        		"    \"name\": \"Visa\", \r\n" + 
		        		"    \"value\": \""+paymode+"\"\r\n" + 
		        		"  }, \r\n" + 
		        		"  \"Line\": [\r\n" + 
		        		"    {\r\n" + 
		        		"      \"DetailType\": \"AccountBasedExpenseLineDetail\", \r\n" + 
		        		"      \"Amount\": "+amt+", \r\n" + 
		        		"      \"AccountBasedExpenseLineDetail\": {\r\n" + 
		        		"        \"AccountRef\": {\r\n" + 
		        		"          \"name\": \"Meals and Entertainment\", \r\n" + 
		        		"          \"value\": \"13\"\r\n" + 
		        		"        }\r\n" + 
		        		"      }\r\n" + 
		        		"    }\r\n" + 
		        		"  ]\r\n" + 
		        		"}";
		        System.out.println("json FORMAT+++++++++++-"+json);
		        
		        OutputStream os = conn.getOutputStream();
		           os.write(json.getBytes("UTF-8"));
		           os.close();

		        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        String output;
		        StringBuffer response = new StringBuffer();
		        while ((output = in.readLine()) != null) {
		            response.append(output);
		            response.append("\r");
		        }
		        in.close();
		        JSONObject obj_JSONObject = XML.toJSONObject(response.toString());
		        jsonString=obj_JSONObject.toString();
		        
		       System.out.println("create purchase jsonString--"+jsonString);
		        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in creating purchase " + e);
		}
		return jsonString;
	}
	
		
	@RequestMapping("/connectToQuickbooks")
    public String connectToQuickbooks(HttpSession session,HttpServletResponse response) {
		System.out.println("inside connectToQuickbooks+++++>>>>>>");
          String clientId = env.getProperty("OAuth2AppClientId"); 
          String  clientSecret = env.getProperty("OAuth2AppClientSecret"); 
          OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(clientId, clientSecret)
                  .callDiscoveryAPI(com.intuit.oauth2.config.Environment.SANDBOX).buildConfig();
          
          String redirectUri = env.getProperty("OAuth2AppRedirectUri"); 
          String csrf = oauth2Config.generateCSRFToken();      
         session.setAttribute("csrfToken", csrf);
          try {           
               List<Scope> scopes = new ArrayList<Scope>();
               scopes.add(Scope.Accounting);
               //Get the authorization URL
               String url = oauth2Config.prepareUrl(scopes, redirectUri, csrf); //redirectUri - pass the callback url
               try {
                    response.sendRedirect(url);
               } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               }
          } catch (InvalidRequestException e) {
               logger.error("Exception calling connectToQuickbooks ", e);
          }
          return null;
    }
    
      @RequestMapping("/oauth2redirect")
        public String  callBackFromOAuth(@RequestParam("code") String authCode, @RequestParam("state") String state,
        		@RequestParam(value = "realmId", required = false) String realmId, HttpSession session) {   
    	  System.out.println("inside oauth2redirect+++++>>>>>>");
            try {
                  String csrfToken = (String) session.getAttribute("csrfToken");
                  if (csrfToken.equals(state)) {
                      session.setAttribute("realmId", realmId);
                      session.setAttribute("auth_code", authCode);
          
                      OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
                      String redirectUri = env.getProperty("OAuth2AppRedirectUri"); 
                     
                      BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);
                          
                      session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
                      session.setAttribute("refresh_token",bearerTokenResponse.getRefreshToken());
                      
                      logger.info("refresh_token==>>" + bearerTokenResponse.getRefreshToken()+"access_token==>>"+bearerTokenResponse.getAccessToken());
                      // Update your Data store here with user's AccessToken and RefreshToken along with the realmId
                      return "Dashboard";
                  }
                  logger.info("csrf token mismatch " );
            } catch (OAuthException e) {
               logger.error("Exception in callback handler ", e);
               }
			 return "redirect:/loginpage";
        }

}
