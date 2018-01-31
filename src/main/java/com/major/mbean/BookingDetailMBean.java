package com.major.mbean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.major.bean.TimeBean;
import com.major.dao.MajorDb;

@ManagedBean
@ViewScoped
public class BookingDetailMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SessionMBean session;
	private String conditionOne;
	private String conditionTwo;
	private ArrayList<TimeBean> listTime = new ArrayList<TimeBean>();
	private TimeBean selectTime = new TimeBean();
	
	public BookingDetailMBean() {
		session = (SessionMBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionMBean");
		if(listTime != null){
			listTime.clear();
		}
		
		listTime = MajorDb.selectTime();
		System.out.println("testttt");
	}
	
	
	public void chooseConditionOne(){
		System.out.println("choose by username: "+session.getUserProfile().getUsername());
		System.out.println("value Time : "+selectTime.getTime());
		
	}
	
	
	public void chooseConditionTwo(){
		System.out.println("choose by username: "+session.getUserProfile().getUsername());
		System.out.println("value Time : "+selectTime.getTime());
	}


	public String getConditionOne() {
		return conditionOne;
	}


	public void setConditionOne(String conditionOne) {
		this.conditionOne = conditionOne;
	}


	public String getConditionTwo() {
		return conditionTwo;
	}


	public void setConditionTwo(String conditionTwo) {
		this.conditionTwo = conditionTwo;
	}


	public ArrayList<TimeBean> getListTime() {
		return listTime;
	}


	public void setListTime(ArrayList<TimeBean> listTime) {
		this.listTime = listTime;
	}


	public TimeBean getSelectTime() {
		return selectTime;
	}


	public void setSelectTime(TimeBean selectTime) {
		this.selectTime = selectTime;
	}
	

}
