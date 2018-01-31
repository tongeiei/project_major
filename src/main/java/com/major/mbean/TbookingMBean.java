package com.major.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.bean.UserLoginBean;
import com.major.dao.MajorDb;

@ManagedBean
@SessionScoped
public class TbookingMBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(SessionMBean.class);
	private String loginName;
	private boolean customer = false;
	private boolean admin = false;
	private boolean employee = false;
	private UserLoginBean BookingProfile;
	
	
	public TbookingMBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserLoginBean getBookingProfile() {
		return BookingProfile;
	}


	public void setBookingProfile(UserLoginBean bookingProfile) {
		BookingProfile = bookingProfile;
	}
	
//	
//	public void getSession(){
//		loginName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
//		userProfile = MajorDb.selectUserProfile(loginName);
//		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("administrator")){
//			admin = true;
//		}else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("employee")){
//			employee = true;
//		}else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("accounting")){
//			customer = true;
//		}else{
//			customer = true;
//		}
//		log.info("authen :"+admin +"|"+employee +"|"+customer);
//		log.info("userProfile : "+this.getUserProfile().getfName()+"|"+this.getUserProfile().getlName());
//	}
	
	
	
	

}
