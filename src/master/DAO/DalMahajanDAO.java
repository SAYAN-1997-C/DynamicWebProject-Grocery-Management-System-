package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.DalMahajanBillDTO;
import master.DTO.DalMahajanMainDTO;
import master.DTO.MahajanDalDTO;
import master.DTO.StockDalDTO;
import master.utilities.ConnectionFactory;

public class DalMahajanDAO {

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String insert_mahajanMain = "insert into dalmahajan_main values(?,?,?,?,?)";
	String insert_mahajanRice = "insert into mahajan_dal values(?,?,?,?,?)";
	String insert_mahajanBill = "insert into dalmahajan_billing values(?,?,?,?,?)";
	String mahajanmain_upd = "update dalmahajan_main set total_bill=?,total_pay=?,total_due=? where mid=?";
	String select_mahajan = "select total_bill,total_pay,total_due from dalmahajan_main where mid=?";
	String upd_stockRice = "update stock_dal set quantity=? where dal_id=?";
	String select_stockRice = "select quantity from stock_dal where dal_id=?";

	String select_mahajanMain = "select mid from dalmahajan_main";

	String select_MahajanBill = "select total_bill,payment,due from dalmahajan_billing where sdate=? and mid=?";
	String del_MahajanBill = "delete from dalmahajan_billing where sdate=? and mid=?";

	String select_MahajanRice = "select dal_id,quantity from mahajan_dal where sdate=? and mid=?";
	String delete_MahajanRice = "delete from mahajan_dal where sdate=? and mid=?";

	String select_midDate = "select sdate,mid from dalmahajan_billing";
	String duplicateCheck = "select sdate,mid,dal_id from mahajan_dal";

	String delall_mMain = "delete from dalmahajan_main where mid=?";
	String delall_mRice = "delete from mahajan_dal where mid=?";
	String delall_mBilling = "delete from dalmahajan_billing where mid=?";
	String nameSelect = "select mname from dalmahajan_main where mid=?";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertMahajanMain(DalMahajanMainDTO mmdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanMain);
			ps.setString(1, mmdto.getMid());
			ps.setString(2, mmdto.getMname());
			ps.setString(3, mmdto.getTotal_bill());
			ps.setString(4, mmdto.getTotal_pay());
			ps.setString(5, mmdto.getTotal_due());
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertMahajanDal(MahajanDalDTO mrdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanRice);
			ps.setString(1, mrdto.getSdate());
			ps.setString(2, mrdto.getMid());
			ps.setString(3, mrdto.getDal_id());
			ps.setString(4, mrdto.getQuan());
			ps.setString(5, mrdto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(mrdto.getQuan());

			ps = cn.prepareStatement(select_stockRice);
			ps.setString(1, mrdto.getDal_id());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevNofbag = Integer.parseInt(rs.getString(1));
				int number = nnofbag + prevNofbag;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(upd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, mrdto.getDal_id());
				ps.executeUpdate();
			}
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertMahajanBill(DalMahajanBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.setString(3, mbdto.getTotal_bill());
			ps.setString(4, mbdto.getPayment());
			ps.setString(5, mbdto.getDue());
			ps.executeUpdate();

			String bill = mbdto.getTotal_bill();
			String pay = mbdto.getPayment();
			String due = mbdto.getDue();
			double billc, payc, duec;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);
			duec = Double.parseDouble(due);

			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
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

				ps = cn.prepareStatement(mahajanmain_upd);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, mbdto.getMid());
			}
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void ExPaymentMahajan(DalMahajanBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.setString(3, mbdto.getTotal_bill());
			ps.setString(4, mbdto.getPayment());
			ps.setString(5, mbdto.getDue());
			ps.executeUpdate();

			String bill = mbdto.getTotal_bill();
			String pay = mbdto.getPayment();

			double billc, payc;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);

			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
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

				ps = cn.prepareStatement(mahajanmain_upd);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, mbdto.getMid());
			}
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void DelMahajanDalBill(DalMahajanBillDTO mbdto) {

		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill, fpay, fdue;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue - ddue;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(mahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_MahajanRice);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
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
					int number = prevNofbag - nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(upd_stockRice);
					ps.setString(1, snum);
					ps.setString(2, sdto.getDal_id());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(delete_MahajanRice);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void ExPaymentDel(DalMahajanBillDTO mbdto) {
		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill, fpay, fdue;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue + dpay;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(mahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public int CheckDateMid(String sdate, String mid) {

		int flag = 1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_midDate);
			while (rs.next()) {

				if (rs.getString(1).equals(sdate) && rs.getString(2).equals(mid)) {
					flag = 0;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;

	}

	public ArrayList getMid() {
		ArrayList arr = new ArrayList();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_mahajanMain);
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

	public int CheckmahajanOilDuplicate(String sdate, String mid, String oil_id) {
		int flag = 1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(sdate) && rs.getString(2).equals(mid) && rs.getString(3).equals(oil_id)) {
					flag = 0;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;
	}

	public int MidCheck(String mid) {

		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_mahajanMain);
			while (rs.next()) {
				if (rs.getString(1).equals(mid)) {
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

	public String getMname(String mid) {

		String name = null;
		try {
			ps = cn.prepareStatement(nameSelect);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		return name;
	}

	public void DelMahajanAllDetails(String mid) {

		try {
			ps = cn.prepareStatement(delall_mBilling);
			ps.setString(1, mid);
			ps.executeUpdate();
			cn.commit();

			ps = cn.prepareStatement(delall_mRice);
			ps.setString(1, mid);
			ps.executeUpdate();
			cn.commit();

			ps = cn.prepareStatement(delall_mMain);
			ps.setString(1, mid);
			ps.executeUpdate();
			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
}
