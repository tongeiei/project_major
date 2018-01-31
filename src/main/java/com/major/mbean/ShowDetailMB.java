package com.major.mbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.BookingBean;
import com.major.bean.FieldBean;
import com.major.bean.UserBean;
import com.major.dao.MajorDb;

@ManagedBean(name = "showField")
@ViewScoped
public class ShowDetailMB implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ShowDetailMB.class);
	private FieldBean fieldB = new FieldBean();
	private ArrayList<UserBean> listUser = new ArrayList<UserBean>();
	private ArrayList<FieldBean> lsfield = new ArrayList<FieldBean>();
	private ArrayList<BookingBean> lsBooking_byA = new ArrayList<BookingBean>();
	
	

	public ArrayList<FieldBean> list() {

		return MajorDb.selectFiel();
	}

	private UserBean userB = new UserBean();


	public ShowDetailMB() {
		if (listUser != null) {
			System.out.println("testtttt111111");
			listUser.clear();

		}
		System.out.println("testtttt");
		listUser = MajorDb.selectUser();
		/////////////////////
		if (lsfield != null) {
			System.out.println("test field");
			lsfield.clear();
		}
		System.out.println("test");
		lsfield = MajorDb.selectFiel();
		////////////////////////////
		if(lsBooking_byA != null){
			lsBooking_byA.clear();
		}
		lsBooking_byA = MajorDb.selectBookingA();

	}

	public FieldBean getFieldB() {
		return fieldB;
	}

	public void setFieldB(FieldBean fieldB) {
		this.fieldB = fieldB;
	}

	// แก้ไข
	public void onRowEdit(RowEditEvent event) {
		UserBean object = (UserBean) event.getObject();
		System.out.println("name : " + object.getName() + "|" + object.getPhone());
		int res = MajorDb.updateUser(object);
		if (res == 1) {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + object.getUser_name()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + object.getUser_name()));
		}
		listUser = MajorDb.selectUser();
	}

	// ปุ่มบันทึกข้อมูลสนาม
	public void addField() {

		int row = MajorDb.insertField(fieldB);
		if (row == 1) {
			FacesContext.getCurrentInstance().addMessage("field insert", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + fieldB.getField_name()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + fieldB.getField_name()));
		}
		lsfield = MajorDb.selectFiel();

	}

	public void editBooking(RowEditEvent item) {
		BookingBean object = (BookingBean) item.getObject();
		System.out.println("field name : " + object.getField_name() + "|" + object.getStatus_booking());
		
		System.out.println("ss :" + object.getStatus_booking());
		String pay = object.getStatus_booking();
		int res = MajorDb.updateBooking(object);
		if(pay == "ยืนยันการชำระ"){
			if (res == 1) {
				FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
						"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + object.getStatus_booking()));
			} else {
				FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + object.getStatus_booking()));
			}
			lsBooking_byA = MajorDb.selectBookingA();
		}
	
	}
	public void CancelEdit(RowEditEvent event) {
		BookingBean object = (BookingBean) event.getObject();
	}

	
	public void editField(RowEditEvent item) {
		FieldBean object = (FieldBean) item.getObject();
		System.out.println("field id : "+ object.getField_id()  +"field name : " + object.getField_name() + "|" + object.getField_price());
		int res = MajorDb.updateField(object);
		if (res == 1) {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + object.getField_name()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + object.getField_name()));
		}
		lsfield = MajorDb.selectFiel();
	}

	public void onRowCancel(RowEditEvent event) {
		UserBean object = (UserBean) event.getObject();

		// FacesContext.getCurrentInstance().addMessage("res_del", new
		// FacesMessage(FacesMessage.SEVERITY_INFO,
		// "ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + userB.getUser_name()));
	}

	public void cancelBtn(RowEditEvent item) {
		FieldBean object = (FieldBean) item.getObject();
	}
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Car Selected", ((UserBean) event.getObject()).getUser_name());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	    	 FacesMessage msg = new FacesMessage("Car Unselected", ((UserBean) event.getObject()).getUser_name());
	         FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	    
	    

	public void delete(String user) {
		int res = MajorDb.deleteUserTest(user);
		System.out.println(res + "|" + user);

		if (res == 1) {
			FacesContext.getCurrentInstance().addMessage("res_del", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"ผลการดำเนินงาน", "แก้ไขข้อมูลเรียบร้อย " + userB.getUser_name()));
		} else {
			FacesContext.getCurrentInstance().addMessage("res_del", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"ผลการดำเนินงาน", "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + userB.getUser_name()));
		}
		listUser = MajorDb.selectUser();
	}
	

	public void test(SelectEvent event) {
	       UserBean object = (UserBean) event.getObject();
	       
	       System.out.println("test : " + object.getUser_name());
		
		
	}

	public UserBean getUserB() {
		return userB;
	}

	public void setUserB(UserBean userB) {
		this.userB = userB;
	}

	public ArrayList<UserBean> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<UserBean> listUser) {
		this.listUser = listUser;
	}

	public ArrayList<FieldBean> getLsfield() {
		return lsfield;
	}

	public void setLsfield(ArrayList<FieldBean> lsfield) {
		this.lsfield = lsfield;
	}

	public ArrayList<BookingBean> getLsBooking_byA() {
		return lsBooking_byA;
	}

	public void setLsBooking_byA(ArrayList<BookingBean> lsBooking_byA) {
		this.lsBooking_byA = lsBooking_byA;
	}


	
	

}
