package com.major.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.major.bean.TimeBean;
import com.major.dao.MajorDb;

@ManagedBean(name = "showTime")
@SessionScoped
public class ShowTime implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<TimeBean> itemTime = new ArrayList<TimeBean>();
	
	

	public ShowTime() {
		
		if (itemTime != null) {
			System.out.println("clear");
			itemTime.clear();

		}
		System.out.println("not null");
		itemTime = MajorDb.selectTime();
		
		
	}

	public ArrayList<TimeBean> time() {
		return MajorDb.selectTime();
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

	private TimeBean item = new TimeBean();

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Car Selected", ((TimeBean) event.getObject()).getTime());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Car Unselected", ((TimeBean) event.getObject()).getTime());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
