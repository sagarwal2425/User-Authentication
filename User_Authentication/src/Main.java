import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Main")
public class Main extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("UserName").toString();
			String password = request.getParameter("Password").toString();
			String userCode = request.getParameter("USERCODE").toString();
			PrintWriter out = response.getWriter();

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/authDataBase?user=root&password=ImShubham1994&useSSL=false");
			String s = "select * from accountinfo where username='" + username + "' ";
			PreparedStatement q = con.prepareStatement(s);
			ResultSet rs = q.executeQuery();
			int flag = 1;
			if (username != null) {
				while (rs.next()) {
					String upwd = rs.getString(3);
					String ucode = rs.getString(4).toString();
					if (!upwd.equals(password)) {
						out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" +

								"<style> \r\n" + ".pwd{\r\n" + "  background-color:#FE0000;\r\n" + "  color: white;\r\n"
								+ "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body bgcolor=\"#FDF5E6\"><center>\r\n"
								+ "<h3>USER AUTHENTICATION</h3>\r\n"
								+ "<form action=\"http://localhost:8082/User_Authentication/Main\">\r\n"
								+ "  Enter your Username:<input type=\"text\"  name=\"UserName\" value=\"" + username
								+ "\"><br><br>\r\n"
								+ "  Enter your Password:<input type=\"password\" class=\"pwd\" name=\"Password\" value=\""
								+ password + "\"><br><br>\r\n"
								+ "   Enter your Usercode:<input type=\"text\" name=\"USERCODE\" value=\"" + userCode
								+ "\"><br><br> \r\n" + "   <input type=\"Submit\">\r\n"
								+ "<h4>You entered wrong password. Please try again.<h4>" + "</form>\r\n"
								+ "</center>\r\n" + "\r\n" + "</body>\r\n" + "</html>\r\n" + "");
						out.close();
					}
					if (!ucode.equals(userCode)) {
						out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>\r\n"
								+ "USER AUTHENTICATION</title>\r\n" + "<style> \r\n" + ".code{\r\n"
								+ "  background-color:#FE0000;\r\n" + "  color: white;\r\n" + "}\r\n" + "</style>\r\n"
								+ "</head>\r\n" + "<body bgcolor=\"#FDF5E6\"><center>\r\n"
								+ "<h3>USER AUTHENTICATION</h3>\r\n"
								+ "<form action=\"http://localhost:8082/User_Authentication/Main\">\r\n"
								+ "  Enter your Username:<input type=\"text\"  name=\"UserName\" value=\"" + username
								+ "\"><br><br>\r\n"
								+ "  Enter your Password:<input type=\"password\"  name=\"Password\" value=\""
								+ password + "\"><br><br>\r\n"
								+ "   Enter your Usercode:<input type=\"text\" class=\"code\" name=\"USERCODE\" value=\""
								+ userCode + "\" ><br><br> \r\n" + "   <input type=\"Submit\">\r\n"
								+ "<h4>You entered wrong user code. Please try again.</h4>" + "</form>\r\n"
								+ "</center>\r\n" + "\r\n" + "</body>\r\n" + "</html>\r\n" + "");
						out.close();
					}
					out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>\r\n"
							+ "USER AUTHENTICATION</title>\r\n" + "<style> \r\n" + "input[type=text] {\r\n"
							+ "  background-color: #3CBC8D;\r\n" + "  color: black;\r\n" + "}\r\n" + "</style>\r\n"
							+ "</head>\r\n" + "<body bgcolor=\"#FDF5E6\"><center>\r\n"
							+ "<h3>USER AUTHENTICATION</h3>\r\n"
							+ "<form action=\"http://localhost:8082/User_Authentication/Main\">\r\n"
							+ "  Enter your Username:<input type=\"text\" name=\"UserName\" value=\"" + username
							+ "\"><br><br>\r\n"
							+ "  Enter your Password:<input type=\"password\" name=\"Password\" value=\"\" style=\" background-color:#3CBC8D;"
							+ password + "\"><br><br>\r\n"
							+ "   Enter your Usercode:<input type=\"text\" name=\"USERCODE\" value=\"" + userCode
							+ "\"><br><br> \r\n" + "   <input type=\"Submit\">\r\n"
							+ "<h4>Authentication is successful</h4>" + "</form>\r\n" + "</center>\r\n" + "\r\n"
							+ "</body>\r\n" + "</html>\r\n" + "");
					flag = 0;
					out.close();
				}
			}
			if (flag == 1) {
				out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>\r\n"
						+ "USER AUTHENTICATION</title>\r\n" + "<style> \r\n" + ".user {\r\n"
						+ "  background-color:#FE0000;\r\n" + "  color: white;\r\n" + "}\r\n" + "</style>\r\n"
						+ "</head>\r\n" + "<body bgcolor=\"#FDF5E6\"><center>\r\n" + "<h3>USER AUTHENTICATION</h3>\r\n"
						+ "<form action=\"http://localhost:8082/User_Authentication/Main\">\r\n"
						+ "  Enter your Username:<input type=\"text\" class=\"user\" name=\"UserName\" value=\""
						+ username + "\" ><br><br>\r\n"
						+ "  Enter your Password:<input type=\"password\" name=\"Password\" value=\"" + password
						+ "\"><br><br>\r\n" + "   Enter your Usercode:<input type=\"text\" name=\"USERCODE\" value=\""
						+ userCode + "\"><br><br> \r\n" + "   <input type=\"Submit\">\r\n"
						+ "<h4>You entered wrong username. Please try again.<h4>" + "</form>\r\n" + "</center>\r\n"
						+ "\r\n" + "</body>\r\n" + "</html>\r\n" + "");
				out.close();
			}
		} catch (Exception e) {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
