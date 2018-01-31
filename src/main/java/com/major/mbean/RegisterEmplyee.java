package com.major.mbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.UserBean;
import com.major.dao.MajorDb;

@ManagedBean(name = "emplyee")
@ViewScoped
public class RegisterEmplyee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegisterEmplyee.class);
	private UserBean employee_bean = new UserBean();
	
	private SessionMBean session;

	public RegisterEmplyee() {
		super();
		employee_bean = new UserBean();
		setSession((SessionMBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("sessionMBean"));
		
	}
	
//    //ปุ่มบันทึกข้อมูลสมาชิก
//	public void onBtnSubmit() {
//		
//		RequestContext context = RequestContext.getCurrentInstance(); 
//		try {
//			int row = MajorDb.insertEmployee(employee_bean);
//			if(row == 1){
//				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ผลการดำเนินการ","บันทึกข้อมูลสำเร็จ");
//				context.showMessageInDialog(message);
//			}
//		} catch (Exception e) {
//			log.error("Error While onBtnSubmit : "+e.getMessage());
//			e.printStackTrace();
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ผลการดำเนินการ","เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง");
//			context.showMessageInDialog(message);
//		}
//
//	}
	
	
	
	
	
	

	public UserBean getEmployee_bean() {
		return employee_bean;
	}

	public void setEmployee_bean(UserBean employee_bean) {
		this.employee_bean = employee_bean;
	}

	public SessionMBean getSession() {
		return session;
	}

	public void setSession(SessionMBean session) {
		this.session = session;
	}
	
	
	

}
