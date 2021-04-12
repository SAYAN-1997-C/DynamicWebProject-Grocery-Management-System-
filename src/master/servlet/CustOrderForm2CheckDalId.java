package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCustomerDAO;

/**
 * Servlet implementation class CustOrderForm2CheckDalId
 */
public class CustOrderForm2CheckDalId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String dal_id=request.getParameter("dal_id");
		
		int flag1=1;
		DalCustomerDAO cdao=new DalCustomerDAO();
		flag1=cdao.CheckCustDalDuplicate(cdate, cid, dal_id);
		if(flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("CustomerDalAddServe");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Already added this bag please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("DalCustOrderForm2.jsp");
			rd.forward(request, response);
		}
	}

}
