package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCashDAO;
import master.DTO.CashDalDTO;

/**
 * Servlet implementation class CashDalAddServe
 */
public class CashDalAddServe extends HttpServlet {
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
		String quan=request.getParameter("quan");
		String price=request.getParameter("price");
		
		CashDalDTO cdto=new CashDalDTO();
		cdto.setCash_date(cash_date);
		cdto.setBillno(billno);
		cdto.setDal_id(dal_id);
		cdto.setQuan(quan);
		cdto.setPrice(price);
		
		DalCashDAO cdao=new DalCashDAO();
		cdao.InsertCashDal(cdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalCashOrderForm2.jsp");
		rd.forward(request, response);
	}

}
