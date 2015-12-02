package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Connector;
import persistence.UserUtil;
import users.User;

@WebServlet("/testold")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/bs.jsp").forward(request, response);
		//if (request.getSession().getAttribute("user") != null) {
			//doTest(response.getWriter());
		//}
	}

	private void doTest(PrintWriter pw) {
		pw.print("testing\n");
		try {
			Connector.getConnection();
			pw.print("connection estabilished");
		} catch (ClassNotFoundException | SQLException e) {
			pw.print(e.getMessage() + "<hr/>");
			for (StackTraceElement err : e.getStackTrace()) {
				pw.print(err + "<br/>");
			}
		}
		User u = new User("user", "user@mail.com", "user", 1000, 10, 4, 6, 0);
		try {
			UserUtil.addUser(u);
			pw.print("user added succesfully");
		} catch (ClassNotFoundException | NoSuchAlgorithmException
				| SQLException e) {
			pw.print("catched SQLException");
			e.printStackTrace();}
			catch(Exception e){
				pw.print("unknown ex "+e.getClass().getName());
			}
		
		List<User> users_got=null;
		try {
			users_got = UserUtil.getAllUsers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (User user : users_got) {
			pw.print(user+"<br/>");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
