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
import org.unibl.etf.ip.bean.CategoryBean;
import org.unibl.etf.ip.bean.FitnessUserBean;
import org.unibl.etf.ip.dao.AdminDAO;
import org.unibl.etf.ip.dao.CategoryDAO;
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
		
		if(action == null || "".equals(action)) {
			address = "/WEB-INF/login.jsp";
		} else if("showcategory".equals(action)) {
			address = "/WEB-INF/category.jsp";
			extractCategories(ses);
		} else if("showfitnessusers".equals(action)) {
			address = "/WEB-INF/fitness.jsp";
			extractUsers(ses);
		} else if("showstats".equals(action)) {
			address = "/WEB-INF/stats.jsp";
		} else if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			AdminBean adminBean = AdminDAO.selectOne(username, password);
			if(adminBean != null) {
				address = "/WEB-INF/home.jsp";
			} else {
				ses.setAttribute("notification", "Username/password not correct!");
			}
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
		}
		
		//PrintWriter pw = response.getWriter();
		
		/*pw.println("<td><form action=\"?action=updateCategory\" method=\"post\">\r\n"
				+ "                <button class=\"btn btn-primary\">Update</button>\r\n"
				+ "                <input type=\"text\" style=\"display: none;\" value=\""+"\">\r\n"
				+ "            </form></td>\r\n"
				+ "            <td><form action=\"?action=deleteCategory\" method=\"post\">\r\n"
				+ "                <button class=\"btn btn-primary\">Delete</button>\r\n"
				+ "                <input type=\"text\" style=\"display: none;\" value=\""+"\">\r\n"
				+ "            </form></td>");*/
		//pw.close();
		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
