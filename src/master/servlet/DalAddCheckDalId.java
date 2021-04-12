package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.StockDalDAO;

/**
 * Servlet implementation class DalAddCheckDalId
 */
public class DalAddCheckDalId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String dal_id=request.getParameter("dal_id");
		
		StockDalDAO sdao=new StockDalDAO();
		int flag=0;
		flag=sdao.CheckDalId(dal_id);
		if(flag==1)
		{

			request.setAttribute("error", "Dal ID already exists.");
			RequestDispatcher rd=request.getRequestDispatcher("DalAdd.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("DalAddServe");
			rd.forward(request, response);
		}
	}

}
