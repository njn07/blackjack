package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.PersistenceException;
import persistence.UserManager;
import users.User;
import utils.Utils;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BALANCE = 3000;
	UserManager manager = UserManager.getManager();

	public registerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userLogin = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(Utils.isNullOrEmpty(userLogin,email,password)){
			response.sendRedirect("registration.jsp");
			return;
		}
		try {
			manager.addUser(new User(userLogin, email, password, DEFAULT_BALANCE,
					0, 0, 0,0));
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			request.setAttribute("errorMessage", "This login already exists");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
			return;
		}
		redirectSuccess(request,response);
	}

	private void redirectSuccess(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect("login");		
	}

}
