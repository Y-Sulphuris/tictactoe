package com.tictactoe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logic")
public class LogicServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int index = getSelectedIndex(req);
		resp.sendRedirect("./");
	}
	
	private int getSelectedIndex(HttpServletRequest req) {
		String click = req.getParameter("click");
		boolean isNumeric = click.chars().allMatch(Character::isDigit);
		return isNumeric ? Integer.parseInt(click) : 0;
	}
}
