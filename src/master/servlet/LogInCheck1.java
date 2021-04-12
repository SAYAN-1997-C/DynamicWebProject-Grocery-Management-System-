package master.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.utilities.ConnectionFactory;

/**
 * Servlet implementation class LogInCheck1
 */
public class LogInCheck1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gmail = request.getParameter("gmail");
		String pass = request.getParameter("pass");
		int flag = 0;

		ConnectionFactory obj = new ConnectionFactory();
		Connection cn = obj.getCon();
		Statement st = null;
		ResultSet rs = null;
		String select_sql = "select gmail,password from admin_signup";
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_sql);
			while (rs.next()) {
				if ((rs.getString(1).equals(gmail)) && (rs.getString(2).equals(pass))) {
					flag = 1;
					response.sendRedirect("HomeMain.jsp");
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if (flag == 0) {
			request.setAttribute("error", "You've entered wrong username/password");
			RequestDispatcher rd = request.getRequestDispatcher("LogIn.jsp");
			rd.forward(request, response);
		}
		obj.releaseConnectionSourceST(rs, st, cn);
	}

}
