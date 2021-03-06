package unit09;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */

import com.saeyan.javabeans.MemberBean;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberBean member = new MemberBean("전수빈","skybluehyun");
		
		request.setAttribute("member", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("P338_el.jsp");
		dispatcher.forward(request, response);
	}

}
