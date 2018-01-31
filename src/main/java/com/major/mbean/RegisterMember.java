package com.major.mbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.UserBean;
import com.major.dao.MailSender;
import com.major.dao.MajorDb;

@ManagedBean(name = "register")
@ViewScoped
public class RegisterMember implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegisterMember.class);
	private UserBean user_bean = new UserBean();
	private UserBean detail_user = new UserBean();
	private ArrayList<UserBean> lsmember;
	private UserBean select;
	private SessionMBean session;

	public UserBean getUser_bean() {
		return user_bean;
	}

	public void setUser_bean(UserBean user_bean) {
		this.user_bean = user_bean;
	}

	public RegisterMember() {
		super();
		user_bean = new UserBean();
		session = (SessionMBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("sessionMBean");
	}
     //ปุ่มบันทึกข้อมูลสมาชิก
	public void onBtnSubmit() {
		
		RequestContext context = RequestContext.getCurrentInstance(); 
		log.info("user : " + user_bean.getUser_name());
		System.out.println("print : " + user_bean.getName());
		try {
			int row = MajorDb.insertUser(user_bean);
			if(row == 1){
				MailSender mailSender = new MailSender();
				mailSender.sendMail(user_bean);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ผลการดำเนินการ","บันทึกข้อมูลสำเร็จ");
				context.showMessageInDialog(message);
			}
		} catch (Exception e) {
			log.error("Error While onBtnSubmit : "+e.getMessage());
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ผลการดำเนินการ","เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง");
			context.showMessageInDialog(message);
		}

	}
	
	public void updateUser(){
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			int row = MajorDb.updateUser(user_bean);
			if(row == 1){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ผลการดำเนินการ","แก้ไขข้อมูลสำเร็จ");
				context.showMessageInDialog(message);
			}
		} catch (Exception e) {
			log.error("Error While onBtnSubmit : "+e.getMessage());
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ผลการดำเนินการ","เกิดข้อผิดพลาดกรุณาลองใหม่อีกครั้ง");
			context.showMessageInDialog(message);
		}
		
	}
	

	


	public UserBean getDetail_user() {
		return detail_user;
	}

	public void setDetail_user(UserBean detail_user) {
		this.detail_user = detail_user;
	}

	public UserBean getSelect() {
		return select;
	}

	public void setSelect(UserBean select) {
		this.select = select;
	}

	public ArrayList<UserBean> getLsmember() {
		return lsmember;
	}

	public void setLsmember(ArrayList<UserBean> lsmember) {
		this.lsmember = lsmember;
	}

}
