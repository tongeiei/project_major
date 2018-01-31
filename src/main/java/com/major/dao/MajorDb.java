package com.major.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.lowagie.iia.Book;
import com.major.bean.BookingBean;
import com.major.bean.FieldBean;
import com.major.bean.TimeBean;
import com.major.bean.UserBean;
import com.major.bean.UserLoginBean;
import com.major.mbean.SessionMBean;
import com.major.util.DBUtil;

public class MajorDb {

	private static String major_db = "jdbc/major_db";
	private static Logger log = Logger.getLogger(MajorDb.class);
	private SessionMBean session;
	private String login;

	public MajorDb() {
		session = (SessionMBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("sessionMBean");
		getUsername();
		// TODO Auto-generated constructor stub
	}

	public void getUsername() {
		login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

	}

	// เพิ่มข้อมูลสมาชิก
	public static int insertUser(UserBean userbean) {

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmtOne = null;
		PreparedStatement stmtTwo = null;
		ResultSet rst = null;

		StringBuilder sbUser = new StringBuilder();
		sbUser.append("INSERT INTO member_users");
		sbUser.append("(user_name,user_pass,user_full_name,");
		sbUser.append("name,lastname,pic,email,address,phone,point)");
		sbUser.append(" VALUES (?,?,?,");
		sbUser.append("?,?,?,?,?,?,?)");
		String sqlAddNewMember = new String(sbUser);

		int respon = 0;
		try {
			// String username = null;
			String full_name = userbean.getUser_full_name();
			full_name = "accounting";
			// setMD5///////////////////////////////////////
			String password = userbean.getUser_pass();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sbd = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sbd.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sbd.toString();
			/////////////////////////////////////////////
			conn.setAutoCommit(false);
			stmtOne = conn.prepareStatement(sqlAddNewMember, PreparedStatement.RETURN_GENERATED_KEYS);
			stmtOne.setString(1, userbean.getUser_name());
			stmtOne.setString(2, password);
			stmtOne.setString(3, full_name);
			stmtOne.setString(4, userbean.getName());
			stmtOne.setString(5, userbean.getLastname());
			stmtOne.setBinaryStream(6, userbean.getPic().getInputstream());
			stmtOne.setString(7, userbean.getEmail());
			stmtOne.setString(8, userbean.getAddress());
			stmtOne.setString(9, userbean.getPhone());
			stmtOne.setInt(10, 0);
			respon = stmtOne.executeUpdate();
			rst = stmtOne.getGeneratedKeys();

			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmtOne);

			StringBuilder sbRole = new StringBuilder();
			sbRole.append("INSERT INTO member_user_roles");
			sbRole.append("(user_name,role_name)");
			sbRole.append(" VALUES (?,?)");
			String sqlRole = new String(sbRole);
			stmtTwo = conn.prepareStatement(sqlRole);
			stmtTwo.setString(1, userbean.getUser_name());
			stmtTwo.setString(2, full_name);
			respon = stmtTwo.executeUpdate();

			DBUtil.closeStatement(stmtTwo);

			conn.commit();
		} catch (Exception e) {
			log.error("Error while register : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmtOne);
			DBUtil.closeStatement(stmtTwo);
			DBUtil.closeDbConnection(conn);
		}

		return respon;
	}

	// แก้ไขข้อมูล USER
	public static int updateUser(UserLoginBean bean) {
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		int row = 0;
		StringBuilder stb = new StringBuilder();
		stb.append("UPDATE member_users SET name=?,lastname=?,email=?,address=?,phone=? ");
		stb.append("WHERE user_name =?");
		if (conn != null) {
			try {

				String sql = new String(stb);
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bean.getfName());
				stmt.setString(2, bean.getlName());
				stmt.setString(3, bean.getEmail());
				stmt.setString(4, bean.getAddress());
				stmt.setString(5, bean.getPhone());
				stmt.setString(6, bean.getUsername());
				row = stmt.executeUpdate();

			} catch (Exception e) {
				DBUtil.closeStatement(stmt);
				DBUtil.closeStatement(stmt);
			}
		}

		return row;
	}

	public static int updateUser(UserBean bean) {
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		int row = 0;
		StringBuilder stb = new StringBuilder();
		stb.append("UPDATE member_users SET name=?,lastname=?,email=?,address=?,phone=?,point=? ");
		stb.append("WHERE user_name =?");
		if (conn != null) {
			try {

				String sql = new String(stb);
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bean.getName());
				stmt.setString(2, bean.getLastname());
				stmt.setString(3, bean.getEmail());
				stmt.setString(4, bean.getAddress());
				stmt.setString(5, bean.getPhone());
				stmt.setInt(6, bean.getPoint());
				stmt.setString(7, bean.getUser_name());
				
				row = stmt.executeUpdate();

			} catch (Exception e) {
				DBUtil.closeStatement(stmt);
				DBUtil.closeStatement(stmt);
			}
		}

		return row;
	}

	// เพิ่มข้อมูลสนาม
	public static int insertField(FieldBean bean) {

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmtTwo = null;

		StringBuilder sbUser = new StringBuilder();
		sbUser.append("INSERT INTO tbl_field");
		sbUser.append("(field_name,field_detail,field_price)");
		sbUser.append(" VALUES (?,?,?)");
		String sqlAddNewMember = new String(sbUser);

		int respon = 0;
		try {
			stmtTwo = conn.prepareStatement(sqlAddNewMember);
			stmtTwo.setString(1, bean.getField_name());
			stmtTwo.setString(2, bean.getField_detail());
			stmtTwo.setInt(3, bean.getField_price());

			respon = stmtTwo.executeUpdate();

		} catch (Exception e) {
			log.error("Error while register : " + e.getMessage());
		} finally {

			DBUtil.closeStatement(stmtTwo);
			DBUtil.closeDbConnection(conn);
		}

		return respon;
	}

	// แสดงข้อมูลสนาม
	public static ArrayList<FieldBean> selectFiel() {
		try {
			ArrayList<FieldBean> list_fiel = new ArrayList<FieldBean>();
			Connection conn = DBUtil.getJndiDb(major_db);
			PreparedStatement stmt = null;
			ResultSet rst = null;
			String command = "SELECT * FROM tbl_field";
			boolean found = false;
			stmt = conn.prepareStatement(command);
			rst = stmt.executeQuery();
			while (rst.next()) {
				FieldBean fiel = new FieldBean();
				fiel.setField_id(rst.getString("filed_id"));
				fiel.setField_name(rst.getString("field_name"));
				fiel.setField_detail(rst.getString("field_detail"));
				fiel.setField_price(rst.getInt("field_price"));
				list_fiel.add(fiel);
				found = true;
			}
			DBUtil.closeResultSet(rst);
			if (found) {
				return list_fiel;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;// TODO: handle exception
		}

	}

	// แสดงข้อมูล User
	public static ArrayList<UserBean> showDetailUser(String session) {
		ArrayList<UserBean> lsbean = new ArrayList<UserBean>();
		Connection con = DBUtil.getJndiDb(major_db);

		if (con != null) {
		}
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM member_users ");
			sb.append("WHERE user_name = ?");
			String sqlSb = new String(sb);

			stmt = con.prepareStatement(sqlSb);
			stmt.setString(1, session);
			rst = stmt.executeQuery();
			if (rst.next()) {
				UserBean bean = new UserBean();
				bean.setUser_name(rst.getString("user_name"));
				bean.setUser_pass(rst.getString("user_pass"));
				bean.setName(rst.getString("name"));
				bean.setLastname(rst.getString("lastname"));

				bean.setEmail(rst.getString("email"));
				bean.setAddress(rst.getString("address"));
				bean.setPhone(rst.getString("phone"));
				lsbean.add(bean);
			}

		} catch (Exception e) {
			log.error("Error while getting showDetailUser : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(con);
		}

		return lsbean;

	}

	// แสดงข้อมูลสมาชิค
	public static ArrayList<UserBean> selectUser() {
		try {
			Connection con = DBUtil.getJndiDb(major_db);
			ArrayList<UserBean> user_list = new ArrayList<UserBean>();
			PreparedStatement stmt = null;
			ResultSet rst = null;
			StringBuilder sbUser = new StringBuilder();
			sbUser.append("SELECT * FROM member_users ");
			sbUser.append("WHERE user_full_name = 'accounting'");
			String sqlUsr = new String(sbUser);
			boolean found = false;
			stmt = con.prepareStatement(sqlUsr);
			rst = stmt.executeQuery();

			while (rst.next()) {
				UserBean bean = new UserBean();
				bean.setUser_name(rst.getString("user_name"));
				bean.setUser_pass(rst.getString("user_pass"));
				bean.setName(rst.getString("name"));
				bean.setLastname(rst.getString("lastname"));
				bean.setEmail(rst.getString("email"));
				bean.setAddress(rst.getString("address"));
				bean.setPhone(rst.getString("phone"));
				bean.setPoint(rst.getInt("point"));

				user_list.add(bean);
				Map<String, Boolean> checked = new HashMap<String, Boolean>();
				ArrayList<UserBean> checkedItems = new ArrayList<UserBean>();
				for (UserBean item : user_list) {
					if (checked.get(item.getUser_name()) != null) {
						checkedItems.add(item);
						deleteUserTest(bean.getUser_name());
					}
				}
				found = true;

			}
			DBUtil.closeResultSet(rst);

			if (found) {
				return user_list;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}

	}

	// แสดงข้อมูลพนักงาน
	public static ArrayList<UserBean> selecEmployee() {

		PreparedStatement stmt = null;
		ResultSet rst = null;
		Connection con = DBUtil.getJndiDb(major_db);
		ArrayList<UserBean> user_list = new ArrayList<UserBean>();

		try {

			StringBuilder sbUser = new StringBuilder();
			sbUser.append("SELECT * FROM member_users ");
			sbUser.append("WHERE user_full_name = 'employee'");
			String sqlUsr = new String(sbUser);
			boolean found = false;
			stmt = con.prepareStatement(sqlUsr);
			rst = stmt.executeQuery();

			while (rst.next()) {
				UserBean bean = new UserBean();
				bean.setUser_name(rst.getString("user_name"));
				bean.setUser_pass(rst.getString("user_pass"));
				bean.setName(rst.getString("name"));
				bean.setLastname(rst.getString("lastname"));
				bean.setEmail(rst.getString("email"));
				bean.setAddress(rst.getString("address"));
				bean.setPhone(rst.getString("phone"));
				user_list.add(bean);
				found = true;

			}

			if (found) {
				return user_list;
			} else {
				return null;
			}

		} catch (Exception e) {
			log.error("Error while selecEmployee  : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(con);
		}

		return user_list;

	}

	public static UserLoginBean selectUserProfile(String user) {
		UserLoginBean bean = null;
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String command = "SELECT * FROM member_users WHERE user_name = ?";
			stmt = conn.prepareStatement(command);
			stmt.setString(1, user);
			rst = stmt.executeQuery();
			while (rst.next()) {
				bean = new UserLoginBean();
				bean.setUsername(rst.getString("user_name"));
				bean.setfName(rst.getString("name"));
				bean.setlName(rst.getString("lastname"));
				bean.setEmail(rst.getString("email"));
				bean.setAddress(rst.getString("address"));
				bean.setPhone(rst.getString("phone"));
			}
		} catch (Exception e) {
			log.error("Error While selectUserProfile : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return bean;

	}

	///////
	public static ArrayList<TimeBean> selectTime() {
		try {
			ArrayList<TimeBean> ls_time = new ArrayList<TimeBean>();
			Connection conn = DBUtil.getJndiDb(major_db);
			PreparedStatement stmt = null;
			ResultSet rst = null;
			String command = "SELECT * FROM tbl_time";
			boolean found = false;
			stmt = conn.prepareStatement(command);
			rst = stmt.executeQuery();
			while (rst.next()) {
				TimeBean item = new TimeBean();
				item.setTime(rst.getString("time"));
				item.setIdTime(rst.getInt("id_time"));
				item.setPrice(rst.getInt("price"));
				ls_time.add(item);
				found = true;
			}
			DBUtil.closeResultSet(rst);
			if (found) {
				return ls_time;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;// TODO: handle exception
		}

	}

	// แก้ไขข้อมูล Field
	public static int updateField(FieldBean bean) {
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		int row = 0;
		StringBuilder stb = new StringBuilder();
		stb.append("UPDATE tbl_field SET field_name=?,field_detail=?,field_price=? ");
		stb.append("WHERE filed_id =?");
		if (conn != null) {
			try {

				String sql = new String(stb);
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bean.getField_name());
				stmt.setString(2, bean.getField_detail());
				stmt.setInt(3, bean.getField_price());
				stmt.setString(4, bean.getField_id());

				row = stmt.executeUpdate();

			} catch (Exception e) {
				DBUtil.closeStatement(stmt);
				DBUtil.closeStatement(stmt);
			}
		}

		return row;
	}

	// delete User
	public static int deleteUser(UserBean bean) {
		int res = 0;
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		StringBuilder stb = new StringBuilder();
		stb.append("DELETE FROM member_users ");
		stb.append("WHERE user_name = ?");
		String sqlDelete = new String(stb);

		try {
			stmt = conn.prepareStatement(sqlDelete);

			stmt.setString(1, bean.getUser_name());
			res = stmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return res;

	}

	// delete User test
	public static int deleteUserTest(String user) {
		int res = 0;
		Connection conn = DBUtil.getJndiDb(major_db);

		if (user != null) {
			PreparedStatement stmt = null;
			try {
				String command = "DELETE FROM member_users WHERE user_name = ?";
				stmt = conn.prepareStatement(command);

				stmt.setString(1, user);
				res = stmt.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();

			} finally {
				DBUtil.closeStatement(stmt);
				DBUtil.closeDbConnection(conn);
			}
		}

		return res;

	}

	// booking f600
	public static int addBooking(UserLoginBean bean, BookingBean time_ben) {
		int res = 0;

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;

		StringBuilder sbBooking = new StringBuilder();
		sbBooking.append("INSERT INTO test_booking ");
		sbBooking.append(
				"(detail_booking,date_booking,total_time,amount,field_name,user_name,email,tel,status_booking)");
		sbBooking.append(" VALUES (?,?,?,?,?,?,?,?,?)");
		String sqlCode = new String(sbBooking);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));

		String f_name = time_ben.getField_name();
		String total = time_ben.getTotal_time();
		int amount = time_ben.getAmount();
		String date_conv = fmt.format(time_ben.getDate_booking());

		amount = 600;
		total = "1 ชม";
		f_name = "สนามเล็ก";

		try {

			stmt = conn.prepareStatement(sqlCode);
			stmt.setString(1, time_ben.getDetail_booking());
			stmt.setString(2, date_conv);
			System.out.println("date " + date_conv);
			stmt.setString(3, total);
			stmt.setInt(4, amount);
			stmt.setString(5, f_name);
			stmt.setString(6, bean.getUsername());
			stmt.setString(7, bean.getEmail());
			stmt.setString(8, bean.getPhone());
			stmt.setString(9, "ยังไม่ชำระ");
			res = stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return res;
	}

	// booking f1000
	public static int addBooking1000(UserLoginBean bean, BookingBean time_ben) {
		int res = 0;

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;

		StringBuilder sbBooking = new StringBuilder();
		sbBooking.append("INSERT INTO test_booking ");
		sbBooking.append(
				"(detail_booking,date_booking,total_time,amount,field_name,user_name,email,tel,status_booking)");
		sbBooking.append(" VALUES (?,?,?,?,?,?,?,?,?)");
		String sqlCode = new String(sbBooking);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		String f_name = time_ben.getField_name();
		String total = time_ben.getTotal_time();
		int amount = time_ben.getAmount();
		String date_conv = fmt.format(time_ben.getDate_booking());

		amount = 1000;
		total = "1 ชม";
		f_name = "สนามใหญ่";

		try {
			stmt = conn.prepareStatement(sqlCode);
			stmt.setString(1, time_ben.getDetail_booking());
			stmt.setString(2, date_conv);
			// if (time_ben.getDate_booking() != null) {
			// String date = fmt.format(time_ben.getDate_booking());
			// Object obj = date;
			// if (obj == null) {
			// stmt.setDate(2, null);
			// } else {
			// java.sql.Date dt = java.sql.Date.valueOf(new String(date));
			// stmt.setDate(2, dt);
			// }
			// }
			System.out.println("date " + date_conv);
			stmt.setString(3, total);
			stmt.setInt(4, amount);
			stmt.setString(5, f_name);
			stmt.setString(6, bean.getUsername());
			stmt.setString(7, bean.getEmail());
			stmt.setString(8, bean.getPhone());
			stmt.setString(9, "ยังไม่ชำระ");
			res = stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return res;

	}

	// แก้ไขข้อมูลการจอง
	public static int updateBooking(BookingBean bean) {
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		int row = 0;
		StringBuilder stb = new StringBuilder();
		stb.append("UPDATE test_booking SET status_booking =?");
		stb.append("WHERE id_booking =?");
		if (conn != null) {
			try {

				String sql = new String(stb);
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, bean.getStatus_booking());
				stmt.setInt(2, bean.getId_booking());

				row = stmt.executeUpdate();

			} catch (Exception e) {
				DBUtil.closeStatement(stmt);
			}
		}
		updatePoint(bean.getUser_name());
		return row;
	}

	// add point
	public static int updatePoint(String user) {
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		int row = 0;
		StringBuilder stb = new StringBuilder();
		stb.append("UPDATE member_users SET point = point + 1 ");
		stb.append("WHERE user_name =?");
		if (conn != null) {
			try {

				String sql = new String(stb);
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user);

				row = stmt.executeUpdate();

			} catch (Exception e) {
				DBUtil.closeStatement(stmt);
			}
		}

		return row;
	}

	// Select_Booking byAdmin

	public static ArrayList<BookingBean> selectBookingA() {
		BookingBean item = null;
		ArrayList<BookingBean> ls_booking = new ArrayList<BookingBean>();
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String command = "SELECT * FROM test_booking";
			stmt = conn.prepareStatement(command);
			rst = stmt.executeQuery();
			while (rst.next()) {
				item = new BookingBean();
				item.setId_booking(rst.getInt("id_booking"));
				item.setDetail_booking(rst.getString("detail_booking"));
				item.setTest_date(rst.getString("date_booking"));
				item.setTotal_time(rst.getString("total_time"));
				item.setAmount(rst.getInt("amount"));
				item.setField_name(rst.getString("field_name"));
				item.setUser_name(rst.getString("user_name"));
				item.setEmail(rst.getString("email"));
				item.setTel(rst.getString("tel"));
				item.setStatus_booking(rst.getString("status_booking"));
				ls_booking.add(item);
			}
		} catch (Exception e) {
			log.error("Error While selectUserProfile : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return ls_booking;

	}

	// Select_booking by member
	public static ArrayList<BookingBean> selectBookingProfile(String user) {
		BookingBean item = null;
		ArrayList<BookingBean> ls_booking = new ArrayList<BookingBean>();
		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String command = "SELECT * FROM test_booking WHERE user_name = ?";
			stmt = conn.prepareStatement(command);
			stmt.setString(1, user);
			rst = stmt.executeQuery();
			while (rst.next()) {
				item = new BookingBean();
				item.setId_booking(rst.getInt("id_booking"));
				item.setDetail_booking(rst.getString("detail_booking"));
				item.setTest_date(rst.getString("date_booking"));
				item.setTotal_time(rst.getString("total_time"));
				item.setAmount(rst.getInt("amount"));
				item.setField_name(rst.getString("field_name"));
				item.setUser_name(rst.getString("user_name"));
				item.setEmail(rst.getString("email"));
				item.setTel(rst.getString("tel"));
				item.setStatus_booking(rst.getString("status_booking"));
				ls_booking.add(item);
			}
		} catch (Exception e) {
			log.error("Error While selectUserProfile : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return ls_booking;

	}

	//// ตรวจสอบการจอง
	public static BookingBean valBooling(String detail, String date, String field) {
		BookingBean item = null;

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String command = "SELECT * FROM test_booking WHERE detail_booking = ? AND date_booking = ? AND field_name = ?";
			stmt = conn.prepareStatement(command);
			stmt.setString(1, detail);
			stmt.setString(2, date);
			stmt.setString(3, field);
			rst = stmt.executeQuery();
			while (rst.next()) {
				item = new BookingBean();
				item.setId_booking(rst.getInt("id_booking"));
				item.setDetail_booking(rst.getString("detail_booking"));
				item.setTest_date(rst.getString("date_booking"));
				item.setTotal_time(rst.getString("total_time"));
				item.setAmount(rst.getInt("amount"));
				item.setField_name(rst.getString("field_name"));
				item.setUser_name(rst.getString("user_name"));
				item.setEmail(rst.getString("email"));
				item.setTel(rst.getString("tel"));
				item.setStatus_booking(rst.getString("status_booking"));
			}
		} catch (Exception e) {
			log.error("Error While selectUserProfile : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return item;

	}

	// test
	public static TimeBean timeid(int id) {
		TimeBean item = null;

		Connection conn = DBUtil.getJndiDb(major_db);
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String command = "SELECT * FROM tbl_time WHERE id_time = ?";
			stmt = conn.prepareStatement(command);
			stmt.setInt(1, id);

			rst = stmt.executeQuery();
			while (rst.next()) {
				item = new TimeBean();
				item.setIdTime(rst.getInt("id_time"));
				item.setTime(rst.getString("time"));
				item.setPrice(rst.getInt("price"));

			}
		} catch (Exception e) {
			log.error("Error While selectUserProfile : " + e.getMessage());
		} finally {
			DBUtil.closeResultSet(rst);
			DBUtil.closeStatement(stmt);
			DBUtil.closeDbConnection(conn);
		}

		return item;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
