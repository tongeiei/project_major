package com.major.mbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.BookingBean;
import com.major.bean.UserLoginBean;
import com.major.dao.MajorDb;

@ManagedBean
@SessionScoped
public class SessionMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(SessionMBean.class);
	private String loginName;
	private boolean admin = false;
	private boolean employee = false;
	private boolean customer = false;
	private UserLoginBean userProfile;
	private BookingBean bookingBean;
	private ArrayList<BookingBean> lsbooking_byUsr = new ArrayList<BookingBean>();
	private ArrayList<BookingBean> booking_db = new ArrayList<BookingBean>();
	private BookingBean val = new BookingBean();

	public SessionMBean() {
		getSession();
		lsbooking_byUsr = MajorDb.selectBookingProfile(loginName);
		booking_db = MajorDb.selectBookingA();

		log.info("Login By Username : " + loginName);
	}

	public void getSession() {
		loginName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		userProfile = MajorDb.selectUserProfile(loginName);
		bookingBean = new BookingBean();
		if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("administrator")) {
			admin = true;
		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("employee")) {
			employee = true;
		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("accounting")) {
			customer = true;
		} else {
			customer = true;
		}
		log.info("authen :" + admin + "|" + employee + "|" + customer);
		log.info("userProfile : " + this.getUserProfile().getfName() + "|" + this.getUserProfile().getlName());
	}

	public void submit() {
		log.info("edit profile by : " + this.getUserProfile().getUsername() + "|" + this.getUserProfile().getfName()
				+ "|" + this.getUserProfile().getlName());
		int res = MajorDb.updateUser(userProfile);
		if (res == 1) {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + userProfile.getUsername()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + userProfile.getUsername()));
		}
	}

	public void bookingAdd() {
		log.info("booking by : " + this.getUserProfile().getUsername() + "|" + this.getUserProfile().getfName() + "|"
				+ this.getUserProfile().getlName());
		log.info("detail : " + this.getBookingBean().getDetail_booking() + "|"
				+ this.getBookingBean().getDate_booking());

		int res = MajorDb.addBooking(userProfile, bookingBean);
		if (res == 1) {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "จองสนามเรียบร้อย " + userProfile.getUsername()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + userProfile.getUsername()));
		}
		lsbooking_byUsr = MajorDb.selectBookingProfile(loginName);

	}

	public void bookingAdd2() {
		log.info("booking by : " + this.getUserProfile().getUsername() + "|" + this.getUserProfile().getfName() + "|"
				+ this.getUserProfile().getlName());
		log.info("detail : " + this.getBookingBean().getDetail_booking() + "|"
				+ this.getBookingBean().getDate_booking());
		int res = MajorDb.addBooking1000(userProfile, bookingBean);
		if (res < 0) {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "จองสนามเรียบร้อย " + userProfile.getUsername()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + userProfile.getUsername()));
		}
		lsbooking_byUsr = MajorDb.selectBookingProfile(loginName);

	}

	public void validateBooking1() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = fmt.format(bookingBean.getDate_booking());
		String field = "สนามเล็ก";
		val = MajorDb.valBooling(bookingBean.getDetail_booking(), date, field);
		System.out.println(val);
		System.out.println(date + "|" + bookingBean.getDetail_booking());
		if (val == null) {
			System.out.println("booking succes");
				bookingAdd();
		} else {
			System.out.println("booking unsucces");
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เวลาถูกจองไปแล้ว " + userProfile.getUsername()));
		}

	}
	public void validateBooking2() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = fmt.format(bookingBean.getDate_booking());
		String field = "สนามใหญ่";
		val = MajorDb.valBooling(bookingBean.getDetail_booking(), date, field);
		System.out.println(val);
		System.out.println(date + "|" + bookingBean.getDetail_booking());
		if (val == null) {
			System.out.println("booking succes");
				bookingAdd2();
		} else {
			System.out.println("booking unsucces");
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เวลาถูกจองไปแล้ว " + userProfile.getUsername()));
		}

	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

	public UserLoginBean getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserLoginBean userProfile) {
		this.userProfile = userProfile;
	}

	public BookingBean getBookingBean() {
		return bookingBean;
	}

	public void setBookingBean(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
	}

	public ArrayList<BookingBean> getLsbooking_byUsr() {
		return lsbooking_byUsr;
	}

	public void setLsbooking_byUsr(ArrayList<BookingBean> lsbooking_byUsr) {
		this.lsbooking_byUsr = lsbooking_byUsr;
	}

	public ArrayList<BookingBean> getBooking_db() {
		return booking_db;
	}

	public void setBooking_db(ArrayList<BookingBean> booking_db) {
		this.booking_db = booking_db;
	}

	public BookingBean getVal() {
		return val;
	}

	public void setVal(BookingBean val) {
		this.val = val;
	}

}
