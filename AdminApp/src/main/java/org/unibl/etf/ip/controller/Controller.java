package org.unibl.etf.ip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.mysql.cj.Session;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				address = "/WEB-INF/stats.jsp";
			}  else if("addcategory".equals(action)) {
				String categoryName = request.getParameter("categoryName");
				CategoryBean categoryBean = new CategoryBean();
				categoryBean.setName(categoryName);
				CategoryDAO.insert(categoryBean);
				extractCategories(ses);
				address = "/WEB-INF/category.jsp";
			} else if("addconsultant".equals(action)) {
				String name = request.getParameter("name");
				String lastname = request.getParameter("lastname");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				ConsultantBean user = new ConsultantBean(name, lastname, username, password);
				ConsultantDAO.insert(user);
				extractConsultants(ses);
				address = "/WEB-INF/consultants.jsp";
			} else if("addfitnessuser".equals(action)) {
				String name = request.getParameter("name");
				String lastname = request.getParameter("lastname");
				String city = request.getParameter("city");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String avatar = request.getParameter("avatar");
				String mail = request.getParameter("mail");
				FitnessUserBean user = new FitnessUserBean(name, lastname, city, username, password, avatar, mail, false);
				FitnessUserDAO.insert(user);
				extractUsers(ses);
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
				CategoryBean c = (CategoryBean) ses.getAttribute("catattr");
				AttributeDAO.insert(new AttributeBean(name, value, c.getId()));
				address = "/WEB-INF/category.jsp";
			} else if("updatecategory".equals(action)) {
				String id = request.getParameter("id");
				CategoryBean categoryBean = CategoryDAO.selectOne(Integer.parseInt(id));
				if(categoryBean != null) {
					address = "/WEB-INF/updatecategory.jsp";
					ses.setAttribute("category", categoryBean);
				} else {
					ses.setAttribute("update-category-notification", "No category!");
				}
			} else if("updatecategorypost".equals(action)) {
				CategoryBean cb = (CategoryBean)ses.getAttribute("category");
				cb.setName(request.getParameter("name"));
				CategoryDAO.update(cb);
				extractCategories(ses);
				address = "/WEB-INF/category.jsp";
			} else if("updateuser".equals(action)) {
				System.out.println("in");
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
				cb.setName(request.getParameter("name"));
				cb.setLastName(request.getParameter("lastname"));
				cb.setCity(request.getParameter("city"));
				cb.setUsername(request.getParameter("username"));
				cb.setAvatar(request.getParameter("avatar"));
				cb.setMail(request.getParameter("mail"));
				FitnessUserDAO.update(cb);
				extractUsers(ses);
				address = "/WEB-INF/fitness.jsp";
			} else if("deleteuser".equals(action)) {
				String id = request.getParameter("id");
				FitnessUserDAO.delete(Integer.parseInt(id));
				extractUsers(ses);
				address = "/WEB-INF/category.jsp";
			}
			else if("deletecategory".equals(action)) {
				String id = request.getParameter("id");
				CategoryDAO.delete(Integer.parseInt(id));
				extractCategories(ses);
				address = "/WEB-INF/category.jsp";
			} else if("logout".equals(action)) {
				ses.invalidate();
				address = "/WEB-INF/login.jsp";
			} else  {
				address = "/WEB-INF/login.jsp";
			}
		} else if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			AdminBean adminBean = AdminDAO.selectOne(username, password);
			if(adminBean != null) {
				address = "/WEB-INF/home.jsp";
				adminBean.setLoggedIn(true);
				ses.setAttribute("admin", adminBean);
			} else {
				ses.setAttribute("notification", "Username/password not correct!");
			}
		} 
		else {
			address = "/WEB-INF/login.jsp";
		}

		
		//PrintWriter pw = response.getWriter();
		
		/*pw.println("<div class=\"form-group\">\r\n"
				+ "        <label for=\"fileInput\">Attach File:</label>\r\n"
				+ "        <input type=\"file\" class=\"form-control-file\" id=\"fileInput\" name=\"fileInput\">\r\n"
				+ "      </div>");*/
		
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
