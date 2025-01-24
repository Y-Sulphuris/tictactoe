package com.tictactoe;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/logic")
public class LogicServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		Field field = getField(session);
		
		int index = getSelectedIndex(req);
		Sign curSign = field.getField().get(index);
		if (curSign != Sign.VOID) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./");
			dispatcher.forward(req, resp);
			return;
		}
		
		field.getField().put(index, Sign.CROSS);
		
		// win check
		if (checkWin(resp, session, field)) return;
		
		// ai to move
		int emptyIndex = field.getEmptyFieldIndex();
		if (emptyIndex >= 0) {
			field.getField().put(emptyIndex, Sign.NOUGHT);
		} else {
			session.setAttribute("draw", true);
			List<Sign> data = field.getFieldData();
			session.setAttribute("data", data);
			resp.sendRedirect("./");
			return;
		}
		
		List<Sign> data = field.getFieldData();
		
		session.setAttribute("data", data);
		session.setAttribute("field", field);
		
		resp.sendRedirect("./");
	}
	
	private boolean checkWin(HttpServletResponse resp, HttpSession session, Field field) throws IOException {
		Sign winner = field.checkWin();
		if (winner == Sign.CROSS || winner == Sign.NOUGHT) {
			session.setAttribute("winner", winner);
			
			List<Sign> data = field.getFieldData();
			
			session.setAttribute("data", data);
			
			resp.sendRedirect("./");
			return true;
		}
		return false;
	}
	
	private Field getField(HttpSession session) {
		Object fieldAttribute = session.getAttribute("field");
		if (fieldAttribute == null || Field.class != fieldAttribute.getClass()) {
			session.invalidate();
			throw new RuntimeException("Etot dom zaprivachen");
		}
		return (Field) fieldAttribute;
	}
	
	private int getSelectedIndex(HttpServletRequest req) {
		String click = req.getParameter("click");
		boolean isNumeric = click.chars().allMatch(Character::isDigit);
		return isNumeric ? Integer.parseInt(click) : 0;
	}
}
