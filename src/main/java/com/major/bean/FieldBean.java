package com.major.bean;

import java.util.ArrayList;
import java.util.List;

public class FieldBean {
	private String field_id,field_name,field_detail;
	private int field_price;
	public String getField_id() {
		return field_id;
	}
	public void setField_id(String field_id) {
		this.field_id = field_id;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	
	public String getField_detail() {
		return field_detail;
	}
	public void setField_detail(String field_detail) {
		this.field_detail = field_detail;
	}
	public int getField_price() {
		return field_price;
	}
	public void setField_price(int field_price) {
		this.field_price = field_price;
	}
	
//	public List<FieldBean> createCars(int size) {
//        List<FieldBean> list = new ArrayList<FieldBean>();
//        for(int i = 0 ; i < size ; i++) {
//            list.add(new FieldBean());
//        }
//         
//        return list;
//    }
}
