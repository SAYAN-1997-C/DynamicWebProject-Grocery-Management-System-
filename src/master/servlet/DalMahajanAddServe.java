package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalMahajanDAO;
import master.DTO.DalMahajanMainDTO;

/**
 * Servlet implementation class DalMahajanAddServe
 */
public class DalMahajanAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String mid=request.getParameter("mid");
		String mname=request.getParameter("mname");
		String total_bill=request.getParameter("total_bill");
		String total_pay=request.getParameter("total_pay");
		String total_due=request.getParameter("total_due");
		
		DalMahajanMainDTO mdto=new DalMahajanMainDTO();
		mdto.setMid(mid);
		mdto.setMname(mname);
		mdto.setTotal_bill(total_bill);
		mdto.setTotal_pay(total_pay);
		mdto.setTotal_due(total_due);
		
		DalMahajanDAO mdao=new DalMahajanDAO();
		mdao.InsertMahajanMain(mdto);
		request.setAttribute("success", "Mahajan ID saved successfully!!");
		RequestDispatcher rd = request.getRequestDispatcher("DalMahajanAdd.jsp");
		rd.forward(request, response);
	}

}
