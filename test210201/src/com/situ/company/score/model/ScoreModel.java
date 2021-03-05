package com.situ.company.score.model;

public class ScoreModel {
private Integer id;
private String codeEmp;
private String codePro;
private String score;
public ScoreModel(Integer id, String codeEmp, String codePro, String score) {
	super();
	this.id = id;
	this.codeEmp = codeEmp;
	this.codePro = codePro;
	this.score = score;
}
public ScoreModel() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCodeEmp() {
	return codeEmp;
}
public void setCodeEmp(String codeEmp) {
	this.codeEmp = codeEmp;
}
public String getCodePro() {
	return codePro;
}
public void setCodePro(String codePro) {
	this.codePro = codePro;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}



}
