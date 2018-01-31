package com.major.mbean;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.BookingBean;
import com.major.bean.TimeBean;
import com.major.dao.MajorDb;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "booking")
@SessionScoped
public class BookingMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BookingMBean.class);
	private SessionMBean session;
	private BookingBean bookingBean = new BookingBean();
	
	public ArrayList<TimeBean> time() {
		return MajorDb.selectTime();
	}
	private ArrayList<TimeBean> itemTime;
	private TimeBean item = new TimeBean();

	
	
	public void onRowSelect(SelectEvent event) {
		TimeBean item = (TimeBean)event.getObject();
		int id  = item.getIdTime();
		
		
		
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Car Unselected", ((TimeBean) event.getObject()).getTime());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	
	
	
	
	



	public BookingMBean() {
		super();
		item = new TimeBean();
		bookingBean = new BookingBean();
		session = (SessionMBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("sessionMBean");
		// TODO Auto-generated constructor stub
	}
	
	
	public void bookingAdd(){
//		int res = MajorDb.addBooking(bookingBean);
//		System.out.println("detail " + bookingBean.getDetail_booking());
//		if(res == 1){
//			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_INFO, "ผลการดำเนินงาน",  "แก้ไขข้อมูลเรียบร้อย " + bookingBean.getUser_name()));
//		}else{
//			FacesContext.getCurrentInstance().addMessage("res_edit", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ผลการดำเนินงาน",  "เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง " + bookingBean.getUser_name()));
//		}

		
	}
	
	public void selectid(int id){
		TimeBean tt = MajorDb.timeid(id);
		if(tt != null ){
			System.out.println("show time");
			System.out.println(id);
		}
	}
	
	
	
	public BookingBean getBookingBean() {
		return bookingBean;
	}


	public void setBookingBean(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
	}
	public SessionMBean getSession() {
		return session;
	}
	public void setSession(SessionMBean session) {
		this.session = session;
	}

	public TimeBean getItem() {
		return item;
	}

	public void setItem(TimeBean item) {
		this.item = item;
	}

	public ArrayList<TimeBean> getItemTime() {
		return itemTime;
	}

	public void setItemTime(ArrayList<TimeBean> itemTime) {
		this.itemTime = itemTime;
	}

}
