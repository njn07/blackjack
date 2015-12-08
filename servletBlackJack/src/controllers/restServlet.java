package controllers;

import game.Errors;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import persistence.UserManager;
import routing.GamesRouter;
import users.User;
import utils.JSONParser;
import utils.Utils;

@WebServlet("/rest")
public class restServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = UserManager.getManager();
	GamesRouter router = GamesRouter.getInstance();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (Utils.isNullOrEmpty(action)) {
			respond(response, Errors.badParams());
		}
		System.out.println("=== request with action" + action + " ===");
		if (!isRegistered(request)) {
			System.out.println("user is not registered");
			String userId=request.getSession().getId();
			HashMap<String, String> responseResult = router
					.handleUnregisteredUserRequest(userId, action);
			respond(response, responseResult);
		} else {
			System.out.println("user is registered");
			User user=(User)request.getSession().getAttribute("user");
			String login=user.getLogin();
			HashMap<String, String> responseResult = router.handleUserRequest(
					login, action);
			respond(response, responseResult);
		}
	}

	private boolean isRegistered(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user != null;
	}

	private void respond(HttpServletResponse response,
			HashMap<String, String> responseMap)
			throws JsonGenerationException, JsonMappingException, IOException {
		String JSONResult = JSONParser.parseToJson(responseMap);
		ServletOutputStream out = response.getOutputStream();
		allowCrossDomain(response);
		out.print(JSONResult);
	}

	private void allowCrossDomain(HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");

	}

	@Override
	public void destroy() {
		router.killTimer();
	}

}
