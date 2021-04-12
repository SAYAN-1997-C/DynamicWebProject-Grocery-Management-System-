package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.StockDalDTO;
import master.utilities.ConnectionFactory;

public class StockDalDAO {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	String insert_stockRice = "insert into stock_dal values(?,?)";

	String select_stockRice = "select dal_id from stock_dal";
	String select_riceId = "select distinct dal_id from stock_dal";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertStockDal(StockDalDTO sdto) {

		try {
			ps = cn.prepareStatement(insert_stockRice);
			ps.setString(1, sdto.getDal_id());
			ps.setString(2, sdto.getQuan());
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public int CheckDalId(String oil_id) {
		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_stockRice);
			while (rs.next()) {
				if (rs.getString(1).equals(oil_id)) {
					flag = 1;
					break;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		return flag;
	}

	public ArrayList getDalId() {

		ArrayList arr = new ArrayList();

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_riceId);
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

}
