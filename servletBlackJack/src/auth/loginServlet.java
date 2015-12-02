package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.PersistenceException;
import persistence.UserManager;
import users.User;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager = UserManager.getManager();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isLogout(request)) {
			System.out.println("logging out");
			doLogout(request, response);
		} else {
			System.out.println("Logging in");
			doLogin(request, response);
		}
	}

	private void doLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("");
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("name is " + name + " password is " + password);
		if (invalidParams(name, password)) {
			System.out.println("bad params "+name+password);
			loginError(request, response, "Bad username/password");
			return;
		}
		User user;
		try {
			user = userManager.getVerifyUser(name, password);
		} catch (PersistenceException e) {
			System.out.println("no such user");
			user=null;
		}
		if (user != null) {
			System.out.println("user verified");
			session.setMaxInactiveInterval(10 * 60);
			session.setAttribute("user", user);
			response.sendRedirect("user");
		} else {
			System.out.println("no user found");
			String error = "Wrong username/password combination";
			loginError(request, response, error);
		}

	}

	private boolean invalidParams(String name, String password) {
		return (name == (null) || password == (null) || name.isEmpty() || password
				.isEmpty());
	}

	private void loginError(HttpServletRequest request,
			HttpServletResponse response, String error)
			throws ServletException, IOException {
		request.setAttribute("error", error);
		request.getRequestDispatcher("login.jsp").forward(request, response);
		// response.sendRedirect("login.jsp?error=wronginfo");

	}

	private boolean isLogout(HttpServletRequest request) {
		System.out.println(request.getParameter("Logout"));
		return !(request.getParameter("Logout") == null);
	}
}
