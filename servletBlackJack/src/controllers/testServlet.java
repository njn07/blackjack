package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.Connector;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/test")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			doTest(response.getWriter());
		}
	}

	private void doTest(PrintWriter pw) {
		pw.print("testing\n");
		try {
			Connector.getConnection();
			System.out.println("Hoorray");
		} catch (ClassNotFoundException | SQLException e) {
			pw.print(e.getMessage());
			for (StackTraceElement err : e.getStackTrace()) {
				System.out.println(err);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
