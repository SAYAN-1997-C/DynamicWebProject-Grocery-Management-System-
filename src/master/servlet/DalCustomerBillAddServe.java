package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalCustomerDAO;
import master.DTO.DalCustomerBillDTO;

/**
 * Servlet implementation class DalCustomerBillAddServe
 */
public class DalCustomerBillAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String total_bill=request.getParameter("total_bill");
		String payment=request.getParameter("payment");
		String due=request.getParameter("due");
		
		DalCustomerBillDTO cbdto=new DalCustomerBillDTO();
		cbdto.setCdate(cdate);
		cbdto.setCid(cid);
		cbdto.setTotal_bill(total_bill);
		cbdto.setPayment(payment);
		cbdto.setDue(due);
		
		DalCustomerDAO cdao=new DalCustomerDAO();
		cdao.InsertCustBill(cbdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalWelcomeDueCust.jsp");
		rd.forward(request, response);
	}

}
