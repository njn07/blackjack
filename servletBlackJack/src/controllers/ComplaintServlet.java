package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Utils;

@WebServlet("/complain")
public class ComplaintServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597424740864725582L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/view/complaint.jsp").forward(req,
				resp);
	}

	private void redirectSuccess(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/view/complaintsuccess.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String complaintText = req.getParameter("message");
		if (!Utils.isNullOrEmpty(complaintText)) {
			System.out.println("new complaint: " + complaintText);
			redirectSuccess(req, resp);
			return;
		} else {
			req.setAttribute("errormessage", "Complaint must not be empty<br/>");
			req.getRequestDispatcher("WEB-INF/view/complaint.jsp").forward(req,
					resp);
		}
	}
}
