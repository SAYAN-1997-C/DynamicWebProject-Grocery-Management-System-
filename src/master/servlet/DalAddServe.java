package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.StockDalDAO;
import master.DTO.StockDalDTO;

/**
 * Servlet implementation class DalAddServe
 */
public class DalAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String dal_id=request.getParameter("dal_id");
		String quan=request.getParameter("quan");
		
		StockDalDTO sdto=new StockDalDTO();
		sdto.setDal_id(dal_id);
		sdto.setQuan(quan);
		
		StockDalDAO sdao=new StockDalDAO();
		sdao.InsertStockDal(sdto);
		request.setAttribute("saved", "Successfully saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalAdd.jsp");
		rd.forward(request, response);
	}

}
