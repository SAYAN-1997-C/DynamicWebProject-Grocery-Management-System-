package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.CustomerDalDTO;
import master.DTO.DalCustomerBillDTO;
import master.DTO.DalCustomerMainDTO;
import master.DTO.StockDalDTO;
import master.utilities.ConnectionFactory;

public class DalCustomerDAO {

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String insert_custMain = "insert into dalcust_main values(?,?,?,?,?,?)";
	String insert_custRice = "insert into cust_dal values(?,?,?,?,?)";
	String insert_custBill = "insert into dalcust_bill values(?,?,?,?,?)";

	String upd_stockRice = "update stock_dal set quantity=? where dal_id=?";
	String select_stockRice = "select quantity from stock_dal where dal_id=?";

	String upd_custMain = "update dalcust_main set total_bill=?,total_pay=?,total_due=? where cid=?";
	String select_cust = "select total_bill,total_pay,total_due from dalcust_main where cid=?";

	String select_CustCid = "select cid from dalcust_main";

	String select_CustBill = "select total_bill,total_pay,total_due from dalcust_bill where cdate=? and cid=?";
	String delete_CustBill = "delete from dalcust_bill where cdate=? and cid=?";

	String select_CustRice = "select dal_id,quantity from cust_dal where cdate=? and cid=?";
	String delete_CustRice = "delete from cust_dal where cdate=? and cid=?";

	String select_CidDate = "select cdate,cid from dalcust_bill";
	String duplicateCheck = "select cdate,cid,dal_id from cust_dal";

	String delall_cMain = "delete from dalcust_main where cid=?";
	String delall_cRice = "delete from cust_dal where cid=?";
	String delall_cBilling = "delete from dalcust_bill where cid=?";
	String selectCname = "select cname from dalcust_main where cid=?";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertCustMain(DalCustomerMainDTO cmdto) {

		try {
			ps = cn.prepareStatement(insert_custMain);
			ps.setString(1, cmdto.getCid());
			ps.setString(2, cmdto.getCname());
			ps.setString(3, cmdto.getPhno());
			ps.setString(4, cmdto.getTotal_bill());
			ps.setString(5, cmdto.getTotal_pay());
			ps.setString(6, cmdto.getTotal_due());

			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}

	public void InsertCustDal(CustomerDalDTO crdto) {

		try {
			ps = cn.prepareStatement(insert_custRice);
			ps.setString(1, crdto.getCdate());
			ps.setString(2, crdto.getCid());
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

	public void InsertCustBill(DalCustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_custBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.setString(3, cbdto.getTotal_bill());
			ps.setString(4, cbdto.getPayment());
			ps.setString(5, cbdto.getDue());
			ps.executeUpdate();

			String bill = cbdto.getTotal_bill();
			String pay = cbdto.getPayment();
			String due = cbdto.getDue();
			double billc, payc, duec;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);
			duec = Double.parseDouble(due);

			ps = cn.prepareStatement(select_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			if (rs.next()) {
				String PrevBill = rs.getString(1);
				String PrevPay = rs.getString(2);
				String PrevDue = rs.getString(3);

				double PrevBillc, PrevPayc, PrevDuec;
				PrevBillc = Double.parseDouble(PrevBill);
				PrevPayc = Double.parseDouble(PrevPay);
				PrevDuec = Double.parseDouble(PrevDue);

				double fbill, fpay, fdue;
				fbill = PrevBillc + billc;
				fpay = PrevPayc + payc;
				fdue = PrevDuec + duec;

				String fnBill, fnPay, fnDue;
				fnBill = String.valueOf(fbill);
				fnPay = String.valueOf(fpay);
				fnDue = String.valueOf(fdue);

				ps = cn.prepareStatement(upd_custMain);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, cbdto.getCid());
			}
			ps.executeUpdate();
			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}

	public void ExPaymentCust(DalCustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_custBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.setString(3, cbdto.getTotal_bill());
			ps.setString(4, cbdto.getPayment());
			ps.setString(5, cbdto.getDue());
			ps.executeUpdate();

			String bill = cbdto.getTotal_bill();
			String pay = cbdto.getPayment();
			double billc, payc;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);

			ps = cn.prepareStatement(select_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			if (rs.next()) {
				String PrevBill = rs.getString(1);
				String PrevPay = rs.getString(2);
				String PrevDue = rs.getString(3);

				double PrevBillc, PrevPayc, PrevDuec;
				PrevBillc = Double.parseDouble(PrevBill);
				PrevPayc = Double.parseDouble(PrevPay);
				PrevDuec = Double.parseDouble(PrevDue);

				double fbill, fpay, fdue;
				fbill = PrevBillc + billc;
				fpay = PrevPayc + payc;
				fdue = PrevDuec - payc;

				String fnBill, fnPay, fnDue;
				fnBill = String.valueOf(fbill);
				fnPay = String.valueOf(fpay);
				fnDue = String.valueOf(fdue);

				ps = cn.prepareStatement(upd_custMain);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, cbdto.getCid());
			}
			ps.executeUpdate();
			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}

	public void DelCustDalBill(DalCustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(select_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill = 0, fpay = 0, fdue = 0;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue - ddue;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(upd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(delete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			while (rs.next()) {
				StockDalDTO sdto = new StockDalDTO();
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

			ps = cn.prepareStatement(delete_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void ExPaymentDelBill(DalCustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(select_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill = 0, fpay = 0, fdue = 0;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue + dpay;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(upd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(delete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public ArrayList getCid() {
		ArrayList arr = new ArrayList();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CustCid);
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

	public int CheckCustDalDuplicate(String cdate, String cid, String dal_id) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cdate) && rs.getString(2).equals(cid) && rs.getString(3).equals(dal_id)) {
					return 0;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;
	}

	public int CheckDateCid(String cdate, String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CidDate);
			while (rs.next()) {

				if (rs.getString(1).equals(cdate) && rs.getString(2).equals(cid)) {
					return 0;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;

	}

	public int CheckCid(String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CustCid);
			while (rs.next()) {
				if (rs.getString(1).equals(cid))
					return 1;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 0;
	}

	public String getCname(String cid) {

		String name = null;
		try {
			ps = cn.prepareStatement(selectCname);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if (rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		return name;
	}

	public void DelDueCustAllDetails(String cid) {

		try {
			ps = cn.prepareStatement(delall_cBilling);
			ps.setString(1, cid);
			ps.executeUpdate();
			cn.commit();

			ps = cn.prepareStatement(delall_cRice);
			ps.setString(1, cid);
			ps.executeUpdate();
			cn.commit();

			ps = cn.prepareStatement(delall_cMain);
			ps.setString(1, cid);
			ps.executeUpdate();
			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

}
