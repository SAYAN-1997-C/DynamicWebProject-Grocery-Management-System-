package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.CashDalDTO;
import master.DTO.DalCashBillDTO;
import master.DTO.StockDalDTO;
import master.utilities.ConnectionFactory;

public class DalCashDAO {

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	String insert_cashRice = "insert into cash_dal values(?,?,?,?,?)";
	String insert_cashBill = "insert into dalcash_bill values(?,?,?)";

	String upd_stockRice = "update stock_dal set quantity=? where dal_id=?";
	String select_stockRice = "select quantity from stock_dal where dal_id=?";

	String delete_cashRice = "delete from cash_dal where cash_date=? and billno=?";
	String delete_cashBill = "delete from dalcash_Bill where cash_date=? and billno=?";

	String select_CashRice = "select dal_id,quantity from cash_dal where cash_date=? and billno=?";

	String select_CashBill = "select Cash_date,billno from dalcash_bill";
	String duplicateCheck = "select cash_date,billno,dal_id from cash_dal";

	public void InsertCashDal(CashDalDTO crdto) {

		try {
			ps = cn.prepareStatement(insert_cashRice);
			ps.setString(1, crdto.getCash_date());
			ps.setString(2, crdto.getBillno());
			ps.setString(3, crdto.getDal_id());
			ps.setString(4, crdto.getQuan());
			ps.setString(5, crdto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(crdto.getQuan());

			ps = cn.prepareStatement(select_stockRice);
			ps.setString(1, crdto.getDal_id());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevNofbag = Integer.parseInt(rs.getString(1));
				int number = prevNofbag - nnofbag;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(upd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, crdto.getDal_id());
				ps.executeUpdate();
			}
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertCashBill(DalCashBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_cashBill);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.setString(3, cbdto.getTotal_bill());
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void delCashDalBill(DalCashBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(delete_cashBill);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_CashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			rs = ps.executeQuery();
			while (rs.next()) {
				StockDalDTO sdto=new StockDalDTO();
				sdto.setDal_id(rs.getString(1));
				sdto.setQuan(rs.getString(2));
				arr.add(sdto);
			}
			Iterator itr = arr.iterator();
			while (itr.hasNext()) {
				StockDalDTO sdto = (StockDalDTO) itr.next();
				ps = cn.prepareStatement(select_stockRice);
				ps.setString(1, sdto.getDal_id());
				rs = ps.executeQuery();

				int nofbagc = Integer.parseInt(sdto.getQuan());
				if (rs.next()) {

					int prevNofbag = Integer.parseInt(rs.getString(1));
					int number = prevNofbag + nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(upd_stockRice);
					ps.setString(1, snum);
					ps.setString(2, sdto.getDal_id());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(delete_cashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();
			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public int CheckDuplicateBillNODate(String cash_date, String billno) {

		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CashBill);
			while (rs.next()) {
				if (rs.getString(1).equals(cash_date) && rs.getString(2).equals(billno)) {
					flag = 1;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;
	}

	public int CheckCashDalDuplicate(String cash_date, String billno, String dal_id) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cash_date) && rs.getString(2).equals(billno)
						&& rs.getString(3).equals(dal_id)) {
					return 0;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;
	}

}
