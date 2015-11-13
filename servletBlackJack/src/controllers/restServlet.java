package controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import routing.GamesRouter;
import utils.JSONParser;


@WebServlet("/rest")
public class restServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GamesRouter router=new GamesRouter();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String userId=request.getParameter("userId");
		System.out.println("=== new user request from"+userId+" with action" + action+"===");
		HashMap<String, String> responseResult = router.handleUserRequest(
				userId, action);
		String JSONResult = JSONParser.parseToJson(responseResult);
		ServletOutputStream out = response.getOutputStream();
		allowCrossDomain(response);
		out.write(JSONResult.getBytes());
	}
	private void allowCrossDomain(HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
		
	}



}
