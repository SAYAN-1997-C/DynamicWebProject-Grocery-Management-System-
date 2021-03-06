package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CashDAO;

/**
 * Servlet implementation class CashOrderForm1CheckDAteBId
 */
public class CashOrderForm1CheckDAteBId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub.
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		
		int flag=0;
		CashDAO cdao=new CashDAO();
		flag=cdao.CheckDuplicateBillNODate(cash_date, billno);
		if(flag==0) {
			RequestDispatcher rd=request.getRequestDispatcher("CashOrderForm2.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("error", "Already used same Bill No and Date..Please check!!");
			RequestDispatcher rd=request.getRequestDispatcher("CashOrderForm1.jsp");
			rd.forward(request, response);
		}
	}

}
