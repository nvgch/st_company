package com.situ.company.employee.model;

import com.situ.company.util.PagerModel;

public class EmployeeModel extends PagerModel{
private String code;
private String name;
private String pass;
private String codeDept;

private String image;
private String nameDept;

public EmployeeModel(String code, String name, String pass, String codeDept, String image, String nameDept) {
	super();
	this.code = code;
	this.name = name;
	this.pass = pass;
	this.codeDept = codeDept;
	this.image = image;
	this.nameDept = nameDept;
}
public EmployeeModel(String code, String name, String pass, String codeDept, String image) {
	super();
	this.code = code;
	this.name = name;
	this.pass = pass;
	this.codeDept = codeDept;
	this.image = image;

}public EmployeeModel(String code, String name, String pass, String codeDept) {
	super();
	this.code = code;
	this.name = name;
	this.pass = pass;
	this.codeDept = codeDept;
	
}
public EmployeeModel(String code, String name, String pass) {
	super();
	this.code = code;
	this.name = name;
	this.pass = pass;

}
@Override
public String toString() {
	return "EmployeeModel [code=" + code + ", name=" + name + ", pass=" + pass + ", codeDept=" + codeDept + ", image="
			+ image + ", nameDept=" + nameDept + "]";
}
public EmployeeModel(String code, String pass) {
	super();
	this.code = code;
	this.pass = pass;

}

public EmployeeModel(String code) {
	super();
	this.code = code;
	

}
public String getNameDept() {
	return nameDept;
}

public void setNameDept(String nameDept) {
	this.nameDept = nameDept;
}

public EmployeeModel() {
	super();
	// TODO Auto-generated constructor stub
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

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

public String getCodeDept() {
	return codeDept;
}

public void setCodeDept(String codeDept) {
	this.codeDept = codeDept;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

}
