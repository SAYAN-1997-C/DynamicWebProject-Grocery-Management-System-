package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCustomerDAO;

/**
 * Servlet implementation class DalCustOrderForm1CheckDateId
 */
public class DalCustOrderForm1CheckDateId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		
		DalCustomerDAO cdao=new DalCustomerDAO();
		
		int flag=0;
		flag=cdao.CheckDateCid(cdate, cid);
		
		if(flag==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("DalCustOrderForm2.jsp");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Already used same Customer Id and Date..Please check!!");
			RequestDispatcher rd=request.getRequestDispatcher("DalCustOrderForm1.jsp");
			rd.forward(request, response);
		}
	}

}
