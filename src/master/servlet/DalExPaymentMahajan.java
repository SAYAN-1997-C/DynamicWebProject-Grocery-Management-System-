package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.DalMahajanDAO;
import master.DTO.DalMahajanBillDTO;

/**
 * Servlet implementation class DalExPaymentMahajan
 */
public class DalExPaymentMahajan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		String total_bill=request.getParameter("total_bill");
		String payment=request.getParameter("payment");
		String due=request.getParameter("due");
		
		DalMahajanBillDTO mbdto=new DalMahajanBillDTO();
		mbdto.setSdate(sdate);
		mbdto.setMid(mid);
		mbdto.setTotal_bill(total_bill);
		mbdto.setPayment(payment);
		mbdto.setDue(due);
		
		DalMahajanDAO mdao = new DalMahajanDAO();
		mdao.ExPaymentMahajan(mbdto);
		request.setAttribute("done", "Payment Successfull!!");
		RequestDispatcher rd=request.getRequestDispatcher("DalWelcomeMahajan.jsp");
		rd.forward(request, response);
	}

}
