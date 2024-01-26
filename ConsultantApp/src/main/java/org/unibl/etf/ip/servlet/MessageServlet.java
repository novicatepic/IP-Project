package org.unibl.etf.ip.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.unibl.etf.ip.bean.FitnessUserBean;
import org.unibl.etf.ip.bean.MessageBean;
import org.unibl.etf.ip.dao.MessageDAO;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1 MB
	    maxFileSize = 5 * 1024 * 1024,     // 5 MB
	    maxRequestSize = 5 * 5 * 1024 * 1024  // 25 MB
	)
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String configFile = "config/config.txt";   
	private Properties emailProperties;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    

    public void init() throws ServletException {
        try {
            // Load properties from gmail.properties file
            emailProperties = new Properties();
            InputStream input = getServletContext().getResourceAsStream("./mail/gmail.properties");
            emailProperties.load(input);
        } catch (Exception e) {
            throw new ServletException("Error loading email properties", e);
        }
    }
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("start.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseText = request.getParameter("responseText");
        //Part filePart = request.getPart("file");

        for (Part part : request.getParts()) {
        	if(part.getName().equals("file")) {
        		 String fileName = extractFileName(part);
                 InputStream fileContent = part.getInputStream();
                 
                 sendEmail(responseText, fileName, part, request);
        	}
        } 
        MessageDAO.updateOneAnswered(Integer.valueOf(request.getParameter("id")));
        MessageDAO.updateOneRead(Integer.valueOf(request.getParameter("id")));
        HttpSession ses = request.getSession();
        ses.setAttribute("email-notification", "Successful response");
        response.sendRedirect("unansweredmessages.jsp");
	}

	private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
	
	private void sendEmail(String responseText, String fileName, Part filePart, HttpServletRequest request) throws IOException {
		BufferedReader fr = new BufferedReader(
		new InputStreamReader(getServletContext().getResourceAsStream(configFile)));
		String fromFile = fr.readLine();
		fr.close();
		HttpSession ses = request.getSession();
		FitnessUserBean user = (FitnessUserBean)ses.getAttribute("currentUser");
		
	    //String to = "novica.tepic@student.etf.unibl.org";
		String to = user.getMail();
	    String from = fromFile;

	    Session session = Session.getDefaultInstance(emailProperties, new javax.mail.Authenticator() {
	        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	            return new javax.mail.PasswordAuthentication(emailProperties.getProperty("username"), emailProperties.getProperty("password"));
	        }
	    });

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject("Response to Message");
	        message.setText("Response Text: " + responseText);

	        if (!fileName.isEmpty()) {
	            BodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Response Text: " + responseText);

	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);

	            messageBodyPart = new MimeBodyPart();
	            DataSource source = new ByteArrayDataSource(filePart.getInputStream(), filePart.getContentType());
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(fileName);
	            multipart.addBodyPart(messageBodyPart);

	            message.setContent(multipart);
	        }

	        Transport.send(message);
	    } catch (MessagingException | IOException mex) {
	        mex.printStackTrace();
	    }
	}
}
