package com.situ.company.department.model;

import com.situ.company.util.PagerModel;

public class DepartmentModel extends PagerModel{
private String code;
private String name;
private String tel;

public DepartmentModel() {
	super();
	// TODO Auto-generated constructor stub
}
public DepartmentModel(String code, String name, String tel) {
	super();
	this.code = code;
	this.name = name;
	this.tel = tel;
}

public DepartmentModel(String code, String name) {
	super();
	this.code = code;
	this.name = name;
	
}

public DepartmentModel(String code) {
	// TODO Auto-generated constructor stub
	this.code = code;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
@Override
public String toString() {
	return "DepartmentModel [code=" + code + ", name=" + name + ", tel=" + tel + "]";
}



}
