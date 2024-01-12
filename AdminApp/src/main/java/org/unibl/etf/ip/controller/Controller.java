package org.unibl.etf.ip.controller;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.unibl.etf.ip.bean.AdminBean;
import org.unibl.etf.ip.bean.AttributeBean;
import org.unibl.etf.ip.bean.CategoryBean;
import org.unibl.etf.ip.bean.ConsultantBean;
import org.unibl.etf.ip.bean.FitnessUserBean;
import org.unibl.etf.ip.dao.AdminDAO;
import org.unibl.etf.ip.dao.AttributeDAO;
import org.unibl.etf.ip.dao.CategoryDAO;
import org.unibl.etf.ip.dao.ConsultantDAO;
import org.unibl.etf.ip.dao.FitnessUserDAO;
import org.unibl.etf.ip.model.LoggerData;
import org.unibl.etf.ip.model.PasswordHelper;
import org.unibl.etf.ip.password.PasswordHasher;

import com.mysql.cj.Session;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String configFile = "config/config.txt";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = "/WEB-INF/login.jsp";
		String action = request.getParameter("action");
		HttpSession ses = request.getSession();
		
		AdminBean bean = (AdminBean)ses.getAttribute("admin");
		
		if(bean != null) {
			if(action == null || "".equals(action)) {
				address = "/WEB-INF/login.jsp";
			} else if("start".equals(action)) {
				address = "/WEB-INF/home.jsp";
			} else if("showcategory".equals(action)) {
				address = "/WEB-INF/category.jsp";
				extractCategories(ses);
			} else if("showfitnessusers".equals(action)) {
				address = "/WEB-INF/fitness.jsp";
				extractUsers(ses);
			} else if("newconsultant".equals(action)) {
				address = "/WEB-INF/consultants.jsp";
				extractConsultants(ses);
			} else if("showstats".equals(action)) {
				BufferedReader fr = new BufferedReader(
						new InputStreamReader(getServletContext().getResourceAsStream(configFile)));
				String url = fr.readLine();
				fr.close();
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target(url);
				Response apiResponse = target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get();
				LoggerData loggerData = apiResponse.readEntity(LoggerData.class);
				//String[] splitLoggerString = loggerData.getLogData().split("2023");
				String[] splitLoggerString = loggerData.getLogData().split("\n");
				ses.setAttribute("loggerString", splitLoggerString);
				address = "/WEB-INF/stats.jsp";
			}  else if("addcategory".equals(action)) {
				String categoryName = request.getParameter("categoryName");
				if(categoryName != null && !"".equals(categoryName)) {
					CategoryBean categoryBean = new CategoryBean();
					categoryBean.setName(categoryName);
					CategoryDAO.insert(categoryBean);
					extractCategories(ses);
				} else {
					ses.setAttribute("categoryadd", "Empty field for category");
				}
				
				address = "/WEB-INF/category.jsp";
			} else if("addconsultant".equals(action)) {
				String name = request.getParameter("name");
				String lastname = request.getParameter("lastname");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				
				if(name != null && lastname != null && username != null && password != null
						 && !"".equals(name) && !"".equals(lastname) && !"".equals(username) && !"".equals(password)) {
					ConsultantBean user = new ConsultantBean(name, lastname, username, password);
					ConsultantDAO.insert(user);
					extractConsultants(ses);
				} else {
					ses.setAttribute("consultantadd", "Must input all fields!");
				}
				
				address = "/WEB-INF/consultants.jsp";
			} else if("addfitnessuser".equals(action)) {
				BufferedReader fr = new BufferedReader(
						new InputStreamReader(getServletContext().getResourceAsStream(configFile)));
				fr.readLine();
				String url = fr.readLine();
				fr.close();
				String password = request.getParameter("password");
				Client client = ClientBuilder.newClient();
				WebTarget target = client.target(url);
				Response apiResponse = 
						target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
						.post(Entity.entity(new PasswordHelper(password), javax.ws.rs.core.MediaType.APPLICATION_JSON));
				PasswordHelper passwordHelper = apiResponse.readEntity(PasswordHelper.class);
				
				
				String name = request.getParameter("name");
				String lastname = request.getParameter("lastname");
				String city = request.getParameter("city");
				String username = request.getParameter("username");
				String avatar = request.getParameter("avatar");
				String mail = request.getParameter("mail");
				
				if(name != null && lastname != null && city != null && username != null && password != null && mail != null
						 && !"".equals(name) && !"".equals(lastname) && !"".equals(username) && !"".equals(password)
						 && !"".equals(mail) && !"".equals(city)) {
					
					if(password.trim().length() < 8) {
						FitnessUserBean user = new FitnessUserBean(name, lastname, city, username, passwordHelper.getPassword(), avatar, mail, false, false);
						FitnessUserDAO.insert(user);
						extractUsers(ses);
					} else {
						ses.setAttribute("passwordlength", "Password must be more or equal to 8 characters!");
					}
					
					
				} else {
					ses.setAttribute("useradd", "Must input all fields except avatar!");
				}
				
				
				address = "/WEB-INF/fitness.jsp";
			}else if("addattributetocategory".equals(action)) {
				CategoryBean c = CategoryDAO.selectOne(Integer.valueOf(request.getParameter("id")) );
				ses.setAttribute("catattr", c);
				address = "/WEB-INF/attributetocategory.jsp";
			} else if("removeattributefromcategory".equals(action)) {
				String id = request.getParameter("attrId");
				AttributeDAO.delete(Integer.valueOf(id));
				address = "/WEB-INF/category.jsp";
			} else if("finishattributeadd".equals(action)) {
				String name = request.getParameter("attrname");
				String value = request.getParameter("attrvalue");
				
				if(name!=null && value!=null && !"".equals(name) && !"".equals(value)) {
					CategoryBean c = (CategoryBean) ses.getAttribute("catattr");
					AttributeDAO.insert(new AttributeBean(name, value, c.getId()));
				} else {
					ses.setAttribute("attributeadd", "Must input all fields!");
				}
				
				address = "/WEB-INF/category.jsp";
			} else if("updatecategory".equals(action)) {
				String id = request.getParameter("id");
				CategoryBean categoryBean = CategoryDAO.selectOne(Integer.parseInt(id));
				if(id!=null && categoryBean != null) {
					address = "/WEB-INF/updatecategory.jsp";
					ses.setAttribute("category", categoryBean);
				} else {
					ses.setAttribute("update-category-notification", "No category!");
				}
			} else if("updatecategorypost".equals(action)) {
				CategoryBean cb = (CategoryBean)ses.getAttribute("category");
				String name = request.getParameter("name");
				
				if(name!=null && !"".equals(name)) {
					cb.setName(request.getParameter("name"));
					CategoryDAO.update(cb);
					extractCategories(ses);
					address = "/WEB-INF/category.jsp";
				} else {
					ses.setAttribute("update-category-notification2", "Name must not be null!");
					address = "/WEB-INF/updatecategory.jsp";
				}
			} else if("login".equals(action)) {
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					if(username != null && password != null && !"".equals(username) && !"".equals(password)) {
						AdminBean adminBean = AdminDAO.selectOne(username, PasswordHasher.hashPassword(password));
						if(adminBean != null) {
							address = "/WEB-INF/home.jsp";
							adminBean.setLoggedIn(true);
							ses.setAttribute("admin", adminBean);
						} else {
							ses.setAttribute("notification", "Username/password not correct!");
						}
					} else {
						ses.setAttribute("login-invalid", "Login parameters must be entered!");
					}
					
				} catch(NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
			else if("updateuser".equals(action)) {
				String id = request.getParameter("id");
				FitnessUserBean fitnessUser = FitnessUserDAO.selectOne(Integer.parseInt(id));
				if(fitnessUser != null) {
					address = "/WEB-INF/updateuser.jsp";
					ses.setAttribute("fitnessuser", fitnessUser);
				} else {
					ses.setAttribute("update-user-notification", "No user!");
				}
			} else if("updateuserpost".equals(action)) {
				FitnessUserBean cb = (FitnessUserBean)ses.getAttribute("fitnessuser");	
				String name = request.getParameter("name");
				String lastname = request.getParameter("lastname");
				String city = request.getParameter("city");
				String username = request.getParameter("username");
				String mail = request.getParameter("mail");
				
				if(name != null && lastname != null && city != null && username != null && mail != null
						 && !"".equals(name) && !"".equals(lastname) && !"".equals(username)
						 && !"".equals(mail) && !"".equals(city) && cb != null) {
					cb.setName(request.getParameter("name"));
					cb.setLastName(request.getParameter("lastname"));
					cb.setCity(request.getParameter("city"));
					cb.setUsername(request.getParameter("username"));
					cb.setAvatar(request.getParameter("avatar"));
					cb.setMail(request.getParameter("mail"));
					FitnessUserDAO.update(cb);
					extractUsers(ses);
					address = "/WEB-INF/fitness.jsp";
				} else {
					ses.setAttribute("update-user-bad", "Must enter all parameters!");
					address = "/WEB-INF/updateuser.jsp";
				}
				 
				
				
			} else if("deleteuser".equals(action)) {
				String id = request.getParameter("id");
				if(id != null && !"".equals(id)) {
					FitnessUserDAO.updateTerminate(Integer.valueOf(id));
					extractUsers(ses);
				}
				
				address = "/WEB-INF/fitness.jsp";
			} else if("undeleteuser".equals(action)) {
				String id = request.getParameter("id");
				if(id != null && !"".equals(id)) {
					FitnessUserDAO.updateUnterminate(Integer.valueOf(id));
					extractUsers(ses);
				}
				address = "/WEB-INF/fitness.jsp";
			}
			else if("deletecategory".equals(action)) {
				String id = request.getParameter("id");
				if(id != null && !"".equals(id)) {
					CategoryDAO.delete(Integer.parseInt(id));
					extractCategories(ses);
				}	
				address = "/WEB-INF/category.jsp";
			} else if("logout".equals(action)) {
				ses.invalidate();
				address = "/WEB-INF/login.jsp";
			} else  {
				address = "/WEB-INF/login.jsp";
			}
		} else if("login".equals(action)) {
			try {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username != null && password != null && !"".equals(username) && !"".equals(password)) {
					AdminBean adminBean = AdminDAO.selectOne(username, PasswordHasher.hashPassword(password));
					if(adminBean != null) {
						address = "/WEB-INF/home.jsp";
						adminBean.setLoggedIn(true);
						ses.setAttribute("admin", adminBean);
					} else {
						ses.setAttribute("notification", "Username/password not correct!");
					}
				} else {
					ses.setAttribute("login-invalid", "Login parameters must be entered!");
				}
			} catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
		} 
		else {
			address = "/WEB-INF/login.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	private void extractCategories(HttpSession ses) {
		ArrayList<CategoryBean> categories = CategoryDAO.selectAll();
		ses.setAttribute("categories", categories);
	}
	
	private void extractUsers(HttpSession ses) {
		ArrayList<FitnessUserBean> users = FitnessUserDAO.selectAll();
		ses.setAttribute("users", users);
	}
	
	private void extractConsultants(HttpSession ses) {
		ArrayList<ConsultantBean> consultants = ConsultantDAO.selectAll();
		ses.setAttribute("consultants", consultants);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
