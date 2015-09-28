package com.sishuok.es.index.web.entity;

import com.sishuok.es.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* index_advertisements 实体类 
* <p>User: jaylee 
* <p>Date: Mon Sep 28 22:34:01 CST 2015 
* <p>Version: 1.0 
*/ 

@Entity 
@Table(name = "IndexAdvertisements") 
public class IndexAdvertisements extends BaseEntity<Long>{

	@Column(name = "advid")
	private short advid;

	@Column(name = "available")
	private boolean available;

	@Column(name = "type")
	private String type;

	@Column(name = "displayorder")
	private byte displayorder;

	@Column(name = "title")
	private String title;

	@Column(name = "targets")
	private String targets;

	@Column(name = "parameters")
	private String parameters;

	@Column(name = "code")
	private String code;

	@Column(name = "starttime")
	private int starttime;

	@Column(name = "endtime")
	private int endtime;

	public void setAdvid(short advid){
		this.advid = advid;
	}

	public short getAdvid(){
		return advid;
	}

	public void setAvailable(boolean available){
		this.available = available;
	}

	public boolean getAvailable(){
		return available;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDisplayorder(byte displayorder){
		this.displayorder = displayorder;
	}

	public byte getDisplayorder(){
		return displayorder;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setTargets(String targets){
		this.targets = targets;
	}

	public String getTargets(){
		return targets;
	}

	public void setParameters(String parameters){
		this.parameters = parameters;
	}

	public String getParameters(){
		return parameters;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setStarttime(int starttime){
		this.starttime = starttime;
	}

	public int getStarttime(){
		return starttime;
	}

	public void setEndtime(int endtime){
		this.endtime = endtime;
	}

	public int getEndtime(){
		return endtime;
	}
}

