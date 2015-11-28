package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import users.User;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BALANCE = 3000;
	UserManager manager = UserManager.getManager();

	public registerServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userLogin = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		manager.addUser(new User(userLogin, email, password, DEFAULT_BALANCE,
				0, 0, 0));
		redirectSuccess(request,response);
	}

	private void redirectSuccess(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect("login.jsp");		
	}

}
