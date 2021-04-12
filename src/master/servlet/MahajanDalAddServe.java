package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalMahajanDAO;
import master.DTO.MahajanDalDTO;

/**
 * Servlet implementation class MahajanDalAddServe
 */
public class MahajanDalAddServe extends HttpServlet {
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
		String quan=request.getParameter("quan");
		String price=request.getParameter("price");
		
		MahajanDalDTO mdto=new MahajanDalDTO();
		mdto.setSdate(sdate);
		mdto.setMid(mid);
		mdto.setDal_id(dal_id);
		mdto.setQuan(quan);
		mdto.setPrice(price);
		
		DalMahajanDAO mdao=new DalMahajanDAO();
		mdao.InsertMahajanDal(mdto);
		request.setAttribute("success", "saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalStockForm2.jsp");
		rd.forward(request, response);
	}

}
