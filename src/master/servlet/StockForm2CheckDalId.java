package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalMahajanDAO;

/**
 * Servlet implementation class StockForm2CheckDalId
 */
public class StockForm2CheckDalId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		String dal_id=request.getParameter("dal_id");
		
		DalMahajanDAO mdao=new DalMahajanDAO();
		
		int flag=0,flag1=1;
		flag1=mdao.CheckmahajanOilDuplicate(sdate, mid, dal_id);
		if(flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("MahajanDalAddServe");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Already added this Dal please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("DalStockForm2.jsp");
			rd.forward(request, response);
		}
	}

}
