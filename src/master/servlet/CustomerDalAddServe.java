package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCustomerDAO;
import master.DTO.CustomerDalDTO;

/**
 * Servlet implementation class CustomerDalAddServe
 */
public class CustomerDalAddServe extends HttpServlet {
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
		String quan=request.getParameter("quan");
		String price=request.getParameter("price");
		
		CustomerDalDTO cdto=new CustomerDalDTO();
		cdto.setCdate(cdate);
		cdto.setCid(cid);
		cdto.setDal_id(dal_id);
		cdto.setQuan(quan);
		cdto.setPrice(price);
		
		DalCustomerDAO cdao=new DalCustomerDAO();
		cdao.InsertCustDal(cdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalCustOrderForm2.jsp");
		rd.forward(request, response);
	}

}
