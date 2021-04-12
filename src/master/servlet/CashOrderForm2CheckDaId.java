package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCashDAO;

/**
 * Servlet implementation class CashOrderForm2CheckDaId
 */
public class CashOrderForm2CheckDaId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String dal_id=request.getParameter("dal_id");
		
		int flag1=1;
		DalCashDAO cdao=new DalCashDAO();
		flag1=cdao.CheckCashDalDuplicate(cash_date, billno, dal_id);
		if(flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("CashDalAddServe");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Already added this bag please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("DalCashOrderForm2.jsp");
			rd.forward(request, response);
		}
	}

}
